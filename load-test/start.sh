#!/bin/sh
sleep 15
touch results.html
vegeta attack -name=100qps -duration=10s -rate=100 -timeout=1m -targets=targets.list | tee results.100qps.bin | vegeta report
vegeta attack -name=2500qps -duration=10s -rate=2500 -timeout=1m -targets=targets.list | tee results.2500qps.bin | vegeta report

vegeta plot --title="Pet Clinic 100qps" results.100qps.bin > /reports/results-100.html
vegeta plot --title="Pet Clinic 2500qps" results.2500qps.bin > /reports/results-2500.html