# order-managent-system

Get Order details

http://localhost:7000/order-service/v1/api/order-details?orderId=2

{
    "orderVO": {
        "orderId": 2,
        "customerName": "Kanchan",
        "orderDate": "2020-08-19T15:09:23.000+00:00",
        "shippingAddress": "Mumbai",
        "total": 1
    },
    "itemVO": [
        {
            "productCode": 2,
            "productName": "Oppo reno 4",
            "quantity": 1
        }
    ]
}
==============================================================================
Add order details(POST)
http://localhost:7000/order-service/v1/api/add-order

Request Body
{
  "orderVO": {
   "customerName":"Sonal Debuy",
    "orderDate": "2020-08-19T15:09:22.779Z",
    "orderId": 0,
    "shippingAddress": "UP",
    "total": 2
  },
  "productCode": [
    1,2
  ]
}

==============================================================================
Add item order(POST)

http://localhost:7000/order-item/v1/api/add-item

request body:

{
  "productCode": 0,
  "productName": "Nokia 1100",
  "quantity": 2
}

response:

Item added
==============================================================================
Get order item details

http://localhost:7000/order-item/v1/api/order-items?productId=5

{
    "productCode": 5,
    "productName": "Nokia 1100",
    "quantity": 2
}
==============================================================================
Get list of order items

http://localhost:7000/order-item/v1/api/order-items-list?productCodes=1,2

[
    {
        "productCode": 1,
        "productName": "Samsung note 8",
        "quantity": 1
    },
    {
        "productCode": 2,
        "productName": "Oppo reno 4",
        "quantity": 1
    }
]
==============================================================================
Tables
order_item
product_code, product_name, quantity
1, Samsung note 8, 1
2, Oppo reno 4, 1
3, Oppo reno 4, 1
==============================================================================
orderitem_order_mapping
mapping_id, order_id, product_code
1, 1, 1
2, 2, 2
3, 3, 2
4, 4, 1
5, 4, 2
==============================================================================

order_deatils
order_id, customer_name, order_date, shipping_address, total
1, sachin, 2020-08-19 05:30:00, mumbai, 0
2, Kanchan, 2020-08-19 20:39:23, Mumbai, 1
3, rahul, 2020-08-19 20:39:23, Mumbai, 1
4, Ramesh Tiwari, 2020-08-19 20:39:23, UP, 2
5, Om Prakash, 2020-08-19 20:39:23, UP, 2
6, Sonal Debuy, 2020-08-19 20:39:23, UP, 2

swagger link:
order item service swagger link
http://localhost:8092/swagger-ui.html#/

order service swagger link
http://localhost:8093/swagger-ui.html
