import riak as riak

myClient = riak.RiakClient(pb_port=8087)

myBucket = myClient.bucket('s13080')

# wrzuci do bazy dokument
# pobierze go
# wypisze
# zmodyfikuje go
# następnie pobierze
# wypisze
# usunie go
# spróbuje pobrać z bazy

val1 = 1
key1 = myBucket.new('one', data=val1)
key1.store()

fetched1 = myBucket.get('one')


assert val1 == fetched1.data


book = {
    'isbn': "1111979723",
    'title': "Moby Dick",
    'author': "Herman Melville",
    'body': "Call me Ishmael. Some years ago...",
    'copies_owned': 3
}

booksBucket = myClient.bucket('books')
newBook = booksBucket.new(book['isbn'], data=book)
newBook.store()

fetchedBook = booksBucket.get(book['isbn'])

print(fetchedBook.encoded_data)

fetchedBook.delete()