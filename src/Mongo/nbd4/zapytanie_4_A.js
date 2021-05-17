//Średnie, minimalne i maksymalne BMI (waga/wzrost^2) dla osób w bazie, w podziale na narodowości;
printjson(db.people.aggregate([{$project: {_id:1, nationality:1, bmi: [{$divide: [{$toDouble: "$weight"}, {$pow: [{$divide: [{$toDouble: "$height"}, 100]},2]}]}]}}, {$group:{_id:"$nationality",maxBMI:{$max:"$bmi"},minBMI:{$min:"$bmi"}, avgBMI:{$avg:"$bmi"}}}]).toArray())

//nie działa avg - dlaczego?


printjson(db.people.aggregate([{$addFields:{bmi:{$divide:[{$toDouble:"$weight"}, {$pow:[{$divide:[{$toDouble:"$height"}, 100]}, 2]}]}}}, {$group:{_id:"$nationality", maxBMI:{"$max":"$bmi"}, minBMI:{"$min":"$bmi"}, avgBMI:{$avg:"$bmi"}}}]).toArray())

//działa avg