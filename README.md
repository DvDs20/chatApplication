Chat Application

--------------------------------

SQL užduotis:

1. Atrinkti TOP5 prekes, kurios duoda daugausiai pajamų:
   
   `SELECT item_name, SUM(amount) AS total_revenue
   FROM bill_item
   GROUP BY item_name
   ORDER BY total_revenue DESC
   LIMIT 5;`

2. Atrinkti klientus ir mėnesius, kai klientas apmokėjo bendrai už sąskaitas daugiau nei 10:

   `SELECT c.client_id, c.client_name, c.client_surname, EXTRACT(MONTH FROM b.bill_datetime) AS payment_month, SUM(b.amount_due) AS total_amount_paid
    FROM client c
    JOIN bill b ON c.client_id = b.client_id
    GROUP BY c.client_id, c.client_name, c.client_surname, payment_month
    HAVING SUM(b.amount_due) > 10000;`

3. Atrinkti ataskaitą su sekančiais laukais:

   `SELECT 
    c.client_name,
    c.client_surname,
    AVG(DATEDIFF(b2.bill_datetime, b1.bill_datetime)) AS avg_client_bill_interval,
    b1.bill_number AS last_bill_number,
    b1.amount_due AS last_bill_due_amount,
    GROUP_CONCAT(bi.item_name ORDER BY bi.bill_item_id ASC SEPARATOR ', ') AS last_bill_item_list
    FROM client c
    JOIN bill b1 ON c.client_id = b1.client_id
    LEFT JOIN bill b2 ON c.client_id = b2.client_id AND b2.bill_datetime < b1.bill_datetime
    LEFT JOIN bill_item bi ON b1.bill_id = bi.bill_id
    GROUP BY c.client_id, c.client_name, c.client_surname, b1.bill_number, b1.amount_due;`
