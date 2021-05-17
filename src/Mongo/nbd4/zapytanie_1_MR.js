//Średnią wagę i wzrost osób w bazie z podziałem na płeć (tzn. osobno mężczyzn, osobno kobiet);
var mapFunction1 = function() {
    var rec = {
        aheight: parseFloat(this.height),
        aweight: parseFloat(this.weight)
    }
    emit(this.sex, rec);
};

var reduceFunction1 = function(sex, values) {
    var heights = values.map(x => x.aheight)
    var weights = values.map(x => x.aweight)
    return {
        aH: Array.avg(heights),
        aW: Array.avg(weights)
    };
};

db.people.mapReduce(
    mapFunction1,
    reduceFunction1,
    { out: "height_weight_mr" }
)

printjson(db.people.height_weight_mr.find({}).toArray());