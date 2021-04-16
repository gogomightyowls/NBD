import scala.annotation.tailrec

object NBD1 extends App {

  //1. Stwórz 7 elementową listę zawierającą nazwy dni tygodnia.
  val listaDni = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")


  //Napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
  //  a. Pętli for

  def z1a(listaDni: List[String]): String = {
    var stringWynikowy: String = ""
    for(item <- listaDni){
      stringWynikowy += item + ","
    }
    stringWynikowy
  }

  println("z1a " + z1a(listaDni))

  //  b. Pętli for wypisując tylko dni z nazwami zaczynającymi się na „P”
  def z1b(listaDni: List[String]): String = {
    var stringWynikowy: String = ""
    for(item <- listaDni){
      if (item.startsWith("P"))
        stringWynikowy += item + ","
    }
    stringWynikowy
  }

  println("z1b " + z1b(listaDni))

  //  c. Pętli while

  def z1c(listaDni: List[String]): String = {
    var stringWynikowy: String = ""
    var a: Int = 0
    while (a <= listaDni.length - 1){
      stringWynikowy += listaDni(a) + ","
      a += 1
    }
    stringWynikowy
  }

  println("z1c " + z1c(listaDni))


  def z2aa(listaDni: List[String]): String = {
    listaDni match {
      case Nil => ""
      case head :: tail => head + ", " + z2aa(tail)
    }
  }

  println("z2aa" + z2aa(listaDni))

  //  b. Funkcji rekurencyjnej wypisując elementy listy od końca

  def z2b(listaDni: List[String]): String = {
    listaDni match {
      case Nil => ""
      case head :: tail => z2b(tail) + head + ","
    }
  }

  println("z2b " + z2b(listaDni))

  //3. Stwórz funkcję korzystającą z rekurencji ogonowej do zwrócenia oddzielonego przecinkami stringa zawierającego elementy listy z ćwiczenia 1

  def z3(listaDni: List[String]): String = {
    @tailrec
    def iter(listaDni: List[String], result: String): String = {
      if (listaDni.length == 1) result + listaDni.head
      else {
        iter(listaDni.tail, result + listaDni.head + ",")
      }
    }
    iter(listaDni, "")
  }

  println("z3 " + z3(listaDni))

  //4. Dla listy z ćwiczenia 1 napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
  //  a. Metody foldl
  def z4a(listaDni: List[String]):String = {
    listaDni.foldLeft("") ((d1 ,d2) => d1 + d2 + ", ")
  }

  println("z4a " + z4a(listaDni))

  //  b. Metody foldr

  def z4b(listaDni: List[String]):String = {
    listaDni.foldRight("") ((d1 ,d2) => d1 + ", " + d2)
  }

  println("z4b " + z4b(listaDni))


  //  c. Metody foldl wypisując tylko dni z nazwami zaczynającymi się na „P”

  def z4c(listaDni: List[String]):String = {
    val listaP = listaDni.filter(_ startsWith("P"))
    listaP.foldLeft("") ((d1 ,d2) =>  d1 + d2 + ", ")
  }

  println("z4c " + z4c(listaDni))


  //5. Stwórz mapę z nazwami produktów i cenami. Na jej podstawie wygeneruj drugą, z 10% obniżką cen. Wykorzystaj mechanizm mapowania kolekcji.

    val produktCena = Map("rower" -> 10000, "rama" -> 5000, "koła" -> 4000, "kierownica" -> 1000)

  val produktMniejszaCena = produktCena map {
    case (p, c) => p -> 0.9 * c
  }


  //6. Zdefiniuj funkcję przyjmującą krotkę z 3 wartościami różnych typów i wypisującą je
  def z6 (krotka: (String, Int, Double)) = {
    println(krotka)
  }

  z6("Z6 Test",2,2.2)

  //7. Zaprezentuj działanie Option na dowolnym przykładzie (np. mapy, w której wyszukujemy wartości po kluczu)

  def z7(key: Int) = key match{
    case 0 => produktCena.get("kierownica")
    case 1 => produktCena.get("samochód")
  }

  println("z7 " + z7(0))
  println("z7 " + z7(1))

  //8. Napisz funkcję usuwającą zera z listy wartości całkowitych (tzn. zwracającą listę elementów mających wartości różne od 0). Wykorzystaj rekurencję.
val liczby = List(0,1,2,0,4,9,0,6)

  def z8 (liczby: List[Int]): List[Int] = liczby match{
    case Nil => Nil
    case head :: tail => if (head == 0) z8(tail) else head :: z8(tail)
  }

  println("z8 " + z8(liczby))


  //9. Zdefiniuj funkcję, przyjmującą listę liczb całkowitych i zwracającą wygenerowaną na jej podstawie listę, w której wszystkie liczby zostały zwiększone o 1. Wykorzystaj mechanizm mapowania kolekcji.

  def z9(liczby: List[Int]): List[Int] = liczby.map(_ + 1)

println("z9 " + z9(liczby))

  //10. Stwórz funkcję przyjmującą listę liczb rzeczywistych i zwracającą stworzoną na jej podstawie listę zawierającą wartości bezwzględne elementów z oryginalnej listy należących do przedziału <-5,12>. Wykorzystaj filtrowanie.

  val liczbyDouble = List(-6, -5, -3.2, 0, 2, 4, 12, 16)
  def z10(listaLR: List[Double]):List[Double] = {
    listaLR.filter(_ >= -5).filter(_ <= 12).map(_.abs)
  }

  println("z10 " + z10(liczbyDouble))
}
