//Łączną ilość środków pozostałych na kartach kredytowych osób w bazie, w podziale na waluty
printjson(db.people.aggregate([{$unwind:"$credit"},{$project: {"credit.currency": "$credit.currency", "credit.balance": {$toDouble: "$credit.balance"}}}, {$group:{_id:"$credit.currency", sumBalance:{$sum:"$credit.balance"}}}]).toArray())
