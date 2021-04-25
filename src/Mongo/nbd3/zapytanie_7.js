printjson(db.people.find({ height: { $gt: "190"}}).toArray())

printjson(db.people.remove({ height: { $gt: "190"}}))