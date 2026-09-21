#!/usr/bin/env bash
# Sends every sample SOAP request to the running Gateway and prints the
# response. Requires the gateway to be up (docker-compose up, or run
# manually) and listening on localhost:8080.
#
# Usage: ./run_samples.sh

set -e

GATEWAY_URL="http://localhost:8080/ws/library"
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

send() {
    local file="$1"
    local label="$2"
    echo "=================================================================="
    echo "$label"
    echo "------------------------------------------------------------------"
    curl -s -X POST "$GATEWAY_URL" \
        -H "Content-Type: text/xml;charset=UTF-8" \
        -H "SOAPAction: \"\"" \
        -d @"$SCRIPT_DIR/$file" | xmllint --format - 2>/dev/null || \
    curl -s -X POST "$GATEWAY_URL" \
        -H "Content-Type: text/xml;charset=UTF-8" \
        -H "SOAPAction: \"\"" \
        -d @"$SCRIPT_DIR/$file"
    echo
    echo
}

send "1_get_book_details.xml"              "1. getBookDetails — valid book (B001)"
send "2_check_availability.xml"            "2. checkAvailability — valid book (B001)"
send "3_reserve_book.xml"                  "3. reserveBook — valid book, has copies (B001)"
send "4_reserve_book_no_copies_fault.xml"  "4. reserveBook — no copies available -> NoCopiesAvailableFault (B002)"
send "5_book_not_found_fault.xml"          "5. getBookDetails — unknown book -> BookNotFoundFault (B999)"
send "6_invalid_input_fault.xml"           "6. checkAvailability — blank bookId -> InvalidInputFault"

echo "Done. (Install 'xmllint' for pretty-printed XML output, optional.)"
