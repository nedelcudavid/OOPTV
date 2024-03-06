# Tema 3 (Proiect Etapa 2) - POO TV
## Copyright 2022 David Nedelcu 324CA
___________________________________________________________________________________________________

	***Voi prezenta doar functionalitatile noi pentru aceasta etapa, deoarece
	am explicat in prima etapa restul logicii (in vechiul README).***
___________________________________________________________________________________________________

	Functionalitatea de 'subscribe' (actiune on page) este introdusa. Aceasta
permite utilizatorului sa se aboneze unul din genurile filmului de pe pagina de
'see details' pe care se afla daca filmul respectiv il contine si daca nu este deja
in lista acestuia de subscribedGenres. Daca conditiile sunt indeplinite, genul este
adaugat in lista acestuia, in caz contrar se afiseaza eroare.
___________________________________________________________________________________________________
	 
	Funcionalitatea de a adauga sau sterge filme are o parte din logica in clasa
Database este reprezentata de:
- metoda modifyDB() care preia tipul actiunii si executa ce se cere
- metoda addMovie() ce verifica daca filmul deja exista in baza de date; daca acesta
exista vom afisa eroare la output,iar daca nu vom adauga filmul cu ajutorul design
pattern-ului Observer
- metoda deleteMovie() ce verifica daca filmul exista sau nu in baza de date; daca acesta
nu exista vom afisa eroare, iar daca exista il vom sterge de peste tot unde apare cu 
ajutorul design pattern-ului Observer
___________________________________________________________________________________________________
	 
	Functionalitatea de a ne intoarce cu o pagina in urma (clasa BackAction) este
reprezentata de metoda back() ce verifica daca exista un utilizator conectat, apoi verifica
daca mai sunt pagini pe care putem naviga in urma, iar daca ambele conditii sunt indeplinite
in functie de faptul daca pagina pe care urmeaza sa ne intoarcem este 'see details' sau nu
vom extrage pagina sau si pagina si filmul din stivele create pentru acest scop si vom naviga
inapoi cu ajutorul actiunii de 'change page' pe care am modificat-o putin pentru a se potrivi
noilor intrebuintari. Paginile aflate in stive sunt introduse doar daca o actiune de 'change page'
(care nu s-a produs prin intermediul metodei back()) a fost finalizata cu succes.
___________________________________________________________________________________________________

	Functionalitate de a da o recomandare la finalul actiunilor pentru userii premium
sub forma unei notificari a fost implementata in clasa SubscribeAction si este reprezentata
de metoda giveRecommendation(). Aceasta ferifica daca exista user conectat dupa finalizarea
actiunilor si apoi daca acesta este premium. Daca aceste conditii sunt intalnite, se colecteaza
toate genurile disponibile in baza de date curenta, apoi se sorteaza lexicografic. Uremaza sa se
sorteze in functie de numarul de like-uri al fiecrui gen cu ajutorul unui HashMap si apoi parcurse
in ordine de la cel mai apreciat gen. Pe masura ce se parcurg se contruieste si o lista cu cele mai
apreciate filme din fiecare gen care de astemnea se parcurg si se verifica daca a mai fost vazut.
Cand se gaseste primul film care nu a mai fost vazut acesta se recomanda utlizatorului. Daca nu
gasim un astfel de film vom pasa mai departe 'No recommendation'.
___________________________________________________________________________________________________

		***Design Pattern-uri adaugate in aceasta etapa***

- Factory Design Pattern : a fost folosit pentru a construi notificari in functie de ce tip avem
nvoie (ADD/DELETE/Recommendation), iar toata implementarea acestuia se gaseste in package-ul
-notifications_system- unde avem clasa abstracta Notification, cele 3 clase cu notificari specifice
(AddMovieNotification, DeleteMovieNotification si RecommendationNotification) si NotificationFactory
care ne ajuta sa cream pe loc o notificare dupa tip si film. Notification contine de asemenea metoda
addNotificationToUser() ce adauga notificarea curenta in lista de notificari al userului specificat

- Strategy Design Pattern : a fost folosit pentru a implementa metodele de plata disponibile pentru
a cumpara un film, iar toata implementarea se gaseste in package-ul -payment_system- ce contine
clasele PaymentByFreePremiumMovies ce contine metoda de plata folosind filme premium gratis,
PaymentByTokens ce contine metoda de plata folosind tokens, PaymentSystem ce primeste tipul de cont
ce vrea sa cumpere si in functie de asta efectueaza sau nu plata, urmand sa afiseze eroare daca nu
reuseste plata (pe motiv de fonduri insuficiente) si interfata PaymentStrategy ce tine metoda pay().

- Observer Design Pattern : a fost folosit pentru a efectua mai usor schimbarile bazei de date a
filmelor, iar toata implementarea se afla in package-ul -changing_database_system- ce contiine
clasa abstracta Observer cu metoda update(), observerul AddMovieObserver ce este responsabil
de a adauga un film si de a notifica toti utilizatorii care sunt subscribed la cel putin unul din
genurile filmului, observerul DeleteMovieObserver care este responsabil cu stergerea filmului de
peste tot unde exista, notificarea tuturor userilor ce au cumparat filmul respectiv si restituirea
creditelor in functie de tipul contului. Avem de asemnea clasa DatabaseChange ce organizeaza observerii
si reprezinta logica din spatele patternului
___________________________________________________________________________________________________

## Feedback :

   -  Tema aceasta a fost foarte bine explicata, se vede ca se tine cont de
feedback-ul de la cea precedenta. Big up!
___________________________________________________________________________________________________

## Resources:

1. [OCW](https://ocw.cs.pub.ro/courses/poo-ca-cd)
2. [GEEKFORGEEKS](https://www.geeksforgeeks.org/)
3. [TUTORIALSPOINT](https://www.tutorialspoint.com/design_pattern/index.htm)
