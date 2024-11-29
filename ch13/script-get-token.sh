#!/bin/bash

# Define URL, headers, and data
URL="http://localhost:8072/authentication/v1/api/auth"
HEADER="Content-Type: application/json"
DATA='{
    "username": "user@wxauction.com",
    "password": "test123"
}'

# Create a log file for responses
LOG_FILE="curl_responses.log"
ERROR_LOG_FILE="curl_errors.log"

# Initialize log files
echo "Starting requests at $(date)" > "$LOG_FILE"
echo "Error log initialized at $(date)" > "$ERROR_LOG_FILE"

# Loop to send 1000 requests
for i in $(seq 1 1000); do
  echo "Sending Request #$i"

  # Execute the curl command in the background
  curl --silent --location "$URL" \
    --header "$HEADER" \
    --data-raw "$DATA" \
    >> "$LOG_FILE" 2>> "$ERROR_LOG_FILE" &
  
  # Optional: Add a small delay between requests to prevent server overload
  sleep 0.1  # 100 milliseconds
done

# Wait for all background jobs to complete
wait

echo "Finished all requests at $(date)" >> "$LOG_FILE"