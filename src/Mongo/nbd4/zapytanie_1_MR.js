const mapFunction1 = function () {
    var rec = {
        "sH": parseFloat(this.height),
        "sW": parseFloat(this.weight),
        "count": 1
    }
    emit(this.sex, rec);
};

const reduceFunction1 = function (_, values) {
    return {
        "sH": Array.sum(values.map(x => x.sH)),
        "sW": Array.sum(values.map(x => x.sW)),
        "count": Array.sum(values.map(x => x.count))
    };
};

const finalizeFunction = function(_, value) {
    return{
        "aH": (value["sH"] / value["count"]),
        "aW": (value["sW"] / value["count"])
    }
}


db.people.mapReduce(
    mapFunction1,
    reduceFunction1,
    {
        finalize: finalizeFunction,
        out: "height_weight_mr"
    })

printjson(db.height_weight_mr.find({}).toArray());
