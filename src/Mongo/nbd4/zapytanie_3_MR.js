//Listę unikalnych zawodów;

const mapFunction = function (){
    emit({job: this.job}, 1);
};

const reduceFunction = function(id, job) {
    return Array.sum(job);
};


db.people.mapReduce(
    mapFunction,
    reduceFunction,
    {out: "zawody"}
)

printjson(db.zawody.find().toArray())

