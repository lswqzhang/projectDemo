#!/usr/bin/env bash
curl -XGET 'http://10.20.113.245:8080/_cat/indices/mart_finance*?pretty'  | awk -f es.awk