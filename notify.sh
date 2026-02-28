#!/bin/bash

# Script to send notifications via ntfy
NTFY_ENDPOINT="https://ntfy.sh/sms"

send_notification() {
    local message="$1"
    curl -d "$message" "$NTFY_ENDPOINT" 2>/dev/null || true
}

send_notification "Simblast rebrand: Starting remaining tasks..."
