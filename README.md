# SOAP-to-gRPC Translating Gateway — Library Catalog Demo

## Scenario

A library's core catalog/circulation system is a legacy, SOAP-only
application that has been running unchanged for years — too risky and
expensive to rewrite. A new, faster **inventory microservice** has since
been built using **gRPC**, and is used internally by the library's mobile
app. The legacy SOAP system still needs to check book details, availability,
and make reservations — but it only knows how to speak SOAP, and the new
inventory service only speaks gRPC.

The **Gateway** solves this: it exposes a normal-looking SOAP/WSDL endpoint
to the legacy client, and internally translates every call into a gRPC
request against the real inventory service, translating the response (or
error) back into SOAP — without either side needing to change.


## Components

| Folder | Role | Tech |
|---|---|---|
| `grpc-server/` | Real backend — 3 operations, input validation, in-memory catalog | Java, grpc-java |
| `gateway/` | Translating gateway — SOAP endpoint + WSDL on one side, gRPC client on the other, full error mapping | Java, JAX-WS (Metro) + grpc-java |
| `soap-client/` | Legacy-style SOAP client exercising every WSDL operation via JAX-WS `Dispatch` | Java, JAX-WS |
| `sample-requests/` | Raw SOAP XML + a curl script — test every operation without running any client code | curl |

## Operations (defined in both `.proto` and WSDL)

1. **GetBookDetails(bookId)** — title, author, ISBN
2. **CheckAvailability(bookId)** — total vs. available copies
3. **ReserveBook(bookId, memberId)** — reserves one copy, returns a reservation confirmation

## Error mapping (gRPC status <-> SOAP fault)

| gRPC `Status.Code` | SOAP Fault | Meaning |
|---|---|---|
| `INVALID_ARGUMENT` | `InvalidInputFault` | Caller sent bad/missing input (soap:Sender) |
| `NOT_FOUND` | `BookNotFoundFault` | Book ID doesn't exist (soap:Sender) |
| `FAILED_PRECONDITION` | `NoCopiesAvailableFault` | Book exists but has 0 available copies (soap:Sender) |
| anything else (`UNAVAILABLE`, `INTERNAL`, `DEADLINE_EXCEEDED`, ...) | `ServiceUnavailableFault` | Backend/infra problem, not the caller's fault (soap:Receiver) |

This mapping lives in `LibraryCatalogServiceImpl.throwMappedFault(...)` /
`throwMappedFaultWithPrecondition(...)` in the gateway module — it's a
deliberate re-classification, not a blind pass-through of gRPC error codes.

## Seeded catalog data (in `grpc-server`)

| Book ID | Title | Total copies | Available |
|---|---|---|---|
| `B001` | Distributed Systems: Concepts and Design | 3 | 1 |
| `B002` | Designing Data-Intensive Applications | 2 | **0** (triggers `NoCopiesAvailableFault` on reserve) |
| `B003` | Clean Architecture | 4 | 4 |

Any other ID (e.g. `B999`) triggers `BookNotFoundFault`.

## Setup & run (one command)

Requires Docker + Docker Compose, and internet access (to pull Maven
dependencies during the image build).

```bash
docker-compose up --build
```

This starts:
- `grpc-server` on port **9090**
- `gateway` on port **8080**, with its SOAP/WSDL endpoint at
  `http://localhost:8080/ws/library` (WSDL at `...?wsdl`)

## Testing every operation — no client required

```bash
cd sample-requests
./run_samples.sh
```

This sends all 6 sample SOAP requests (3 successful calls + 3 fault
scenarios) via `curl` and prints each SOAP response, including the
`soap:Fault` bodies. You can also load any `.xml` file in that folder
into SoapUI/Postman manually if you prefer a GUI.

## Testing with the SOAP client (full Java client)

```bash
# Gateway must already be running (e.g. via docker-compose up)
cd soap-client
mvn -q clean package -DskipTests
java --add-opens java.base/java.lang=ALL-UNNAMED -jar target/soap-client-1.0.0.jar
```

> The `--add-opens` flag is required because JAX-WS's runtime (Metro) uses
> reflection that the JVM module system blocks by default since Java 16+.

Expected output: real SOAP response XML for the 3 successful calls
(`getBookDetails`, `checkAvailability`, `reserveBook`), followed by 3 caught
`SOAPFaultException`s for the fault scenarios (`NoCopiesAvailableFault`,
`BookNotFoundFault`, `InvalidInputFault`) — demonstrating that gRPC errors
really do arrive at the SOAP client as real, typed SOAP faults.

## Limitations

- The reference WSDL in `gateway/src/main/resources/wsdl/` is a hand-written
  copy for offline review; the authoritative WSDL is generated live by the
  running gateway at `?wsdl`.
- The SOAP client uses JAX-WS's `Dispatch` API (raw XML payloads) rather
  than a typed proxy generated from the WSDL, to avoid a Metro compatibility
  bug on non-Oracle JDKs during WSDL parsing. It still exercises every
  operation and correctly surfaces every SOAP fault type, just via generic
  `SOAPFaultException` rather than distinct typed exceptions client-side.
- No TLS/authentication on either the SOAP or gRPC side (out of scope for
  this demo).
- Catalog data is in-memory only and resets whenever `grpc-server` restarts.
