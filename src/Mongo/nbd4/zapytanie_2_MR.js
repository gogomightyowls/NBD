//2. Łączną ilość środków pozostałych na kartach kredytowych osób w bazie, w podziale na waluty;
const mapFunction = function() {
    for (var i = 0; i < this.credit.length; i++) {
        var key = this.credit[i].currency;
        var value = parseFloat(this.credit[i].balance);

        emit(key, value);
    }
};

const reduceFunction = function(currency, balance) {
    return Array.sum(balance.map(balance=> parseFloat(balance)));
};

db.people.mapReduce(
    mapFunction,
    reduceFunction,
    {out: "credit"}
)

printjson(db.credit.find().toArray())
