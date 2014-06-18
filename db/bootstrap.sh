#!/bin/bash

function insertBaselineData() {
  DATABASE=$1
  PGPASSWORD=postgres psql -U postgres -h localhost -d ${DATABASE} < db/baseline.sql
}

insertBaselineData "trailblazers"
