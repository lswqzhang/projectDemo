#!/usr/bin/env bash
curl -XDELETE 'http://10.20.113.245:8080/_cat/indices/mart_finance*?pretty'



curl -XGET -H 'Authorization:Y29tLnNhbmt1YWkuaXQuZmluLmRpZmY6RUYxQUFFNEVDNUU4QzQ0RTU5QTZBN0M3RTQ1MTQ2MEM=' '10.32.255.194:9200/_cat/indices' |
awk '{print $9}' |
sort |
sed 's/gb//g' |
awk '{sum += $1};END {print sum}'

#   查询bulk request
curl -XGET -H 'Authorization:Y29tLnNhbmt1YWkuaXQuZmluLmRpZmY6RUYxQUFFNEVDNUU4QzQ0RTU5QTZBN0M3RTQ1MTQ2MEM=' '10.32.255.194:9200/_cat/thread_pool?v'

#   查看集群健康状态
curl -XGET -H 'Authorization:Y29tLnNhbmt1YWkuaXQuZmluLmRpZmY6RUYxQUFFNEVDNUU4QzQ0RTU5QTZBN0M3RTQ1MTQ2MEM=' '10.32.255.194:9200/_cluster/health?pretty=true'

#   查看template内容
curl -XGET -H 'Authorization:Y29tLnNhbmt1YWkuaXQuZmluLmRpZmY6RUYxQUFFNEVDNUU4QzQ0RTU5QTZBN0M3RTQ1MTQ2MEM=' '10.32.255.194:9200/_template/fin.es.ss_join.load_check.data?pretty'

#   查看索引信息
curl -XGET -H 'Authorization:Y29tLnNhbmt1YWkuaXQuZmluLmRpZmY6RUYxQUFFNEVDNUU4QzQ0RTU5QTZBN0M3RTQ1MTQ2MEM=' '10.32.255.194:9200/mart_finance.finance_busn_data_int_all_daozong.20170801/_settings?pretty=true'