object NBD2 extends App {
  //  1. Wykorzystaj Pattern Matching w funkcji przyjmującej parametr typu String.
  //  Dla stringów odpowiadających nazwom dni tygodnia funkcja ma zwrócić „Praca” i „Weekend” (odpowiednio dla dni roboczych i wolnych), dla pozostałych napisów „Nie ma takiego dnia”.
  def dniTygodnia(napis: String): String = napis match {
    case "Poniedziałek" | "Wtorek" | "Środa" | "Czwartek" | "Piątek" => "Praca"
    case "Sobota" | "Niedziela" => "Weekend"
    case _ => "Nie ma takiego dnia"
  }

  println(dniTygodnia("Sobota"))
  println(dniTygodnia("Poniedziałek"))
  println(dniTygodnia("Kopytko"))

  //  2. Zdefiniuj klasę KontoBankowe z metodami wplata i wyplata oraz własnością stanKonta - własność ma być tylko do odczytu.
  //  Klasa powinna udostępniać konstruktor przyjmujący początkowy stan konta oraz drugi, ustawiający początkowy stan konta na 0.

  class KontoBankowe(stanPoczatkowyKonta: Double = 0) {
    private var stanKonta: Double = stanPoczatkowyKonta

    def sprawdzStanKonta = stanKonta

    def wplata(kwota: Double) = {
      stanKonta = stanKonta + kwota
    }

    def wyplata(kwota: Double) = {
      if (stanKonta >= kwota) {
        stanKonta = stanKonta - kwota
      }
      else {
        println("nie ma środków na koncie")
      }
    }
  }


  //  3. Zdefiniuj klasę Osoba z własnościami imie i nazwisko.
  //  Stwórz kilka instancji tej klasy.
  //  Zdefiniuj funkcję, która przyjmuje obiekt klasy osoba i przy pomocy Pattern Matching wybiera i zwraca napis zawierający przywitanie danej osoby.
  //  Zdefiniuj 2-3 różne przywitania dla konkretnych osób (z określonym imionami lub nazwiskami) oraz jedno domyślne.

  case class Osoba(imie: String, nazwisko: String) {
  }

  val ms = Osoba("Michał", "St")
  val pt = Osoba("Piotr", "Test")
  val aa = Osoba("Anna", "Abc")
  val mk = Osoba("Magda", "Kowalska")
  val ki = Osoba("ktoś", "inny")

  def przywitanie(osoba: Osoba) = osoba match {
    case Osoba("Michał", "St") => "Cześć " + osoba.imie + " " + osoba.nazwisko + ". Co u Ciebie?"
    case Osoba("Piotr", "Test") => "Cześć " + osoba.imie + " " + osoba.nazwisko + ". Dobrze Cię widzieć!"
    case Osoba("Anna", "Abc") => osoba.imie + " " + osoba.nazwisko + ", miło Cię poznać"
    case Osoba("Magda", "Kowalska") => "Witaj " + osoba.imie + " " + osoba.nazwisko
    case Osoba(_, _) => "Cześć, jak się nazywasz?"
  }


  println(przywitanie(ms))
  println(przywitanie(pt))
  println(przywitanie(aa))
  println(przywitanie(mk))
  println(przywitanie(ki))


  //  4. Zdefiniuj funkcję przyjmującą dwa parametry - wartość całkowitą i funkcję operującą na wartości całkowitej.
  //  Zastosuj przekazaną jako parametr funkcję trzykrotnie do wartości całkowitej i zwróć wynik.

  def twoParamsFunc(int: Int, func: Int => Int): Int = {
    func(func(func(int)))
  }

  def testFunc(int: Int): Int = {
    int * int
  }

  println(twoParamsFunc(2, testFunc))


  //  5. Zdefiniuj klasę Osoba i trzy traity: Student, Nauczyciel, Pracownik.
  //  Osoba powinna mieć własności read only: imie, nazwisko, podatek.
  //  Pracownik powinien mieć własność pensja (z getterem i seterem).
  //  Student i Pracownik powinni przesłaniać własność podatek – dla Studenta zwracamy 0, dla Pracownika 20% pensji.
  //  Nauczyciel powinien dziedziczyć z Pracownika, dla niego podatek zwraca 10% pensji.
  //  Stwórz obiekty z każdym z traitów, pokaż jak podatek działa dla każdego z nich.
  //  Stwórz obiekty z traitami Student i Pracownik, pokaż jak podatek zadziała w zależności od kolejności w jakiej te traity zostały dodane przy tworzeniu obiektu.

  class Osoba5(val imie: String, val nazwisko: String){
    private var _podatek: Double = 0.0
    def podatek = _podatek
  }

  trait Pracownik extends Osoba5 {
    var pensja: Double;
    override def podatek =  0.2 * pensja: Double
  }

  trait Nauczyciel extends Pracownik {
    override def podatek = 0.1 * pensja: Double
  }

  trait Student extends Osoba5 {
    override def podatek =  0.0
  }


  val student = new Osoba5("M", "S") with Student
  val pracownik = new Osoba5("C", "D") with Pracownik {
    override var pensja: Double = 500
  }
  val nauczyciel = new Osoba5("A", "B") with Nauczyciel {
    override var pensja: Double = 1000
  }

  println("student, podatek: " + student.podatek)
  println("nauczyciel, pensja: " + nauczyciel.pensja + " podatek: " + pracownik.podatek)
  println("pracownik, pensja: " + pracownik.pensja + " podatek: " +  pracownik.podatek)


}