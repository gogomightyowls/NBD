//Średnia i łączna ilość środków na kartach kredytowych kobiet narodowości polskiej w podziale na waluty.

printjson(db.people.aggregate([{$match:{nationality:"Poland", sex:"Female"}}, {$unwind:"$credit"}, {$group:{_id:"$credit.currency", avgCreditBalance:{$avg:{$toDouble:"$credit.balance"}}, totalCreditBalance:{$sum:{$toDouble:"$credit.balance"}}}}]).toArray())