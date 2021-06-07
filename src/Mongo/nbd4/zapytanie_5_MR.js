//Średnia i łączna ilość środków na kartach kredytowych kobiet narodowości polskiej w podziale na waluty.

const mapFunction = function (){
    if(this.nationality === 'Poland' && this.sex === 'Female')
        this.credit.forEach(x => emit(x.currency, {'total': parseFloat(x.balance), 'count':1}))
}

const reduceFunction = function (_, values){
    return {
        'total': Array.sum(values.map(x=>x['total'])),
        'count': Array.sum(values.map(x=>x['count']))
    }
}

const finalizeFunction = function(_,value){
    return {
        'total': (value['total']),
        'avg': (value['total'])/(value['count'])
    }
}

db.people.mapReduce(
    mapFunction,
    reduceFunction,
    {
        finalize: finalizeFunction,
        out: "credit"
    })

printjson(db.credit.find({}).toArray());