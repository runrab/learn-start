package com.runrab.gmall.realtime.trade.entry

import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName="order_info")
case class Trade(
                  consignee:String,
                  consignee_tel:String,
                  delivery_address:String,
                  trade_body:String,
                  final_total_amount:Double,
                  create_time:String
                ) extends Serializable {

}
