import requests as requests

person = {'name': 'Michal', 'age': 36, 'hobby': 'cycling', 'max speed': 57.2, 'fav drink': 'coffee'}
key = "M1"


# wrzuci do bazy dokument
r = requests.put('http://localhost:8098/buckets/s13080/keys/M1', json=person).headers
print(r)

# pobierze go
r1 = requests.get('http://localhost:8098/buckets/s13080/keys/M1').text

# wypisze
print(r1)

# zmodyfikuje go
person = {'name': 'Rafal', 'age': 36, 'hobby': 'cycling', 'max speed': 57.2, 'fav drink': 'coffee'}
r2a = requests.put('http://localhost:8098/buckets/s13080/keys/M1', json=person).headers
print(r2a)

# nastepnie pobierze
r2 = requests.get('http://localhost:8098/buckets/s13080/keys/M1').text

# wypisze
print(r2)

# usunie go
r3 = requests.delete('http://localhost:8098/buckets/s13080/keys/M1').headers
print(r3)

# sprobuje pobrac z bazy
r4 = requests.get('http://localhost:8098/buckets/s13080/keys/M1').headers
print(r4)