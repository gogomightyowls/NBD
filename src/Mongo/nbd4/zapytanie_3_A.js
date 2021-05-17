//Listę unikalnych zawodów;
printjson(db.people.aggregate([{$group:{_id:"lista ról", jobs:{$addToSet:{job: "$job"}}}}]).toArray())


//printjson(db.people.aggregate([{_id:"lista ról"},{$addToSet:{job: "$job"}}]).toArray())
//po co "group"?