//Średnie, minimalne i maksymalne BMI (waga/wzrost^2) dla osób w bazie, w podziale na narodowości;
const mapFunction = function() {
    var bmi = function (weight, height) {
        return (weight / Math.pow(height / 100, 2));
    }
    var key = this.nationality;

    var weight = parseFloat(this.weight);
    var height = parseFloat(this.height);
    var value = {
        count: 1,
        sum: bmi(weight, height),
        minBMI: bmi(weight, height),
        maxBMI: bmi(weight, height)
    };

    emit(key, value);

};

const reduceFunction = function (key, values) {
    var valuesObj = { count: 0, sum: 0, minBMI: values[0].minBMI, maxBMI: values[0].maxBMI }

    values.forEach(function (value) {
        valuesObj.count += value.count;
        valuesObj.sum += value.sum;
        valuesObj.minBMI = Math.min(valuesObj.minBMI, value.minBMI);
        valuesObj.maxBMI = Math.max(valuesObj.maxBMI, value.maxBMI);
    });

    return valuesObj;
}

const finalizeFunction = function (key, reducedObject) {
    reducedObject.avgBMI = reducedObject.sum / reducedObject.count;
    delete reducedObject.sum;
    delete reducedObject.count;
    return reducedObject;
}

printjson(db.people.mapReduce(
    mapFunction,
    reduceFunction, {
        finalize: finalizeFunction,
        out: 'bmi'}
    ))


printjson(db.bmi.find().toArray())