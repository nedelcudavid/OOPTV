# Tema 2 (Proiect Etapa 1) - POO TV
## Copyright 2022 David Nedelcu 324CA
___________________________________________________________________________________________________

	Package-ul -input- contine toate clasele predestinate citirii inputului

   Clasele din acest package sunt construite pe modelul celor de la tema 1,
modelate dupa tiparul jsonurilor de input, in asa fel incas sa citim usor inputul.
- clasa InputCredentials are adaaugata metoda subBalance care sa ne ajute sa 
scadem din balance mai usor
___________________________________________________________________________________________________

	Package-ul -platform- contine caracteristicile de baza ale platformei noastre de streaming:

- *Movie* clasa ce extinde InputMovie si contine caracteristicile unui film.
Aceasta are 3 metode in plus care sa ne ajute la prelucrarea datelor din clasa
(addNumLikes, addNumRating si addRating).

- *RegisteredUser* clasa ce extinde InputUser si contine caracteristicile unui
user inregistrat in baza de date a platformei. Aceasta are si ea adaugate 
3 metode care sa faciliteze prelucrarea datelor (subNumFreePremiumMovies,
addTokensCount si subTokensCount).

- *Database* clasa singleton ce reprezina baza de date a platformei. Aceasta retine 
toati userii, filmele, dar si actiunile si se modifica in timp real pe masura ce
platforma este folosita. Aceasta contine 2 metode pentru a gasi indexul userului
curent in baza de date (actiune folositoare cand vrem sa il modificam in baza de date)
si pentru a gasi indexul filmului curent si inca o metoda pentru a goli baza de date.

- *Executable* clasa singleton ce reprezinta executia programului, aceasta ne ajuta sa
stocam pagina curenta, lista curenta de filme si userul curent.Ele se modifica in timp
real pe masura ce userul foloseste platforma. Clasa are 3 metode: run(pune platforma in
miscare si pana la final umple array-ul de output), displayOutputForSuccessfulAction(ce
adauga un nod pentru o actiune reusita in output array) si displayOutputForError(ce adauga
un nod pentru eroare in output array).
___________________________________________________________________________________________________
	 
   Package-ul -pages- contine 2 subpackage-uri:
   
   *-unauthenticated_pages-* ce contine clasele singleton:

- *UnauthenticatedHomepage* (Pagina de home neautentificat)
contine metoda logout ce ne delogheaza si ne muta pe aceasta pagina

- *LoginPage* (Pagina de login)
contine metoda enterLoginPage() ce ne muta pe aceasta pagina

- *RegisterPage* (Pagina de intregistrare)
contine metoda enterRegisterPage() ce ne muta pe aceasta pagina

    *-authenticated_pages-* ce contine clasele singleton:
    
- *AuthenticatedHomepage* (Pagina de home autentificat)
contine metoda enterAuthenticatedHomepage() ce ne muta pe aceasta pagina

- *MoviesPage* (Pagina de filme)
contine metoda showMovies() ce ne muta pe aceasta pagina si ne afiseaza
lista de filme disponibile

- *SeeDetails* (Pagina de selectare film)
contine metoda pickMovie() ce ne muta pe aceasta pagina si ne afiseaza filmul ales

- *Upgrades* (Pagina de inbunatatiri)
contine metoda enterUpgradesPage() ce ne muta pe aceasat pagina

   Toate aceste clase sunt extinse din clasa abstracta Page si au o metoda comuna
validPagesToVisitFromHere() ce retine posibilitatile de navigare din fiecare pagina.
___________________________________________________________________________________________________
	 
   Package-ul -actions- contine actiunile posibile pe platforma noastra:
   
- *ChangePageActions* clasa singleton ce contine metoda execute() ce preia comanda
de schimbare pagina din input si o executa in functie de pagina unde trebuie sa ajungem
daca actiunea nu e permisa se afiseaza output de eroare

   *-on_page_actions-* este package-ul pentru actiunile on page:

- *OnPageActions* clasa singleton ce contine metoda execute() ce preia comanda
actiunii din input si o executa in functie de nume, daca actiunea nu e permisa
se afiseaza output de eroare

- *LoginAction* este clasa utilitara ce realizeaza actiunea de login prin
metoda login() ce cauta userul in baza de date si daca credentialele se
potrivesc se executa metoda statica enterAccount(), tot aici avem si
metoda actualiseInfo() ce se apleaza in metoda enterAccount ce actulizeaza
datele userului logat cu baza de date (in ca ca alti useri au adaugat like-uri
sau ratinguri filmelor). Daca credentialele nu se potrivesc afisam eroare

- *RegisterAction* este clasa utilitara ce realizeaza actiunea de register prin
metoda register() ce cauta userul in baza de date si daca credentialele se
gasesc vom afisa eroare, iar daca nu este permisa inregistrarea

- *SearchAction* este clasa utilitara ce pastreaza in filme curente doar pe 
cele care incep cu un anumit string dat in input

- *FilterAction* este clasa utilitara ce filtreaza filmele curente si le
pastreaza pe cele cu anumiti actori, care au anumite genuri si/sau le sorteaza
dupa rating si durata (crescator sau descrescator)

- *BuyTokensAction* este clasa utilitara ce ne permite sa cumparam tokeni
daca avem destul in balance

- *BuyPremiumAccountAction*  este clasa utilitara ce ne permite sa trecem
la cont premium in caz ca nu avem

- *PurchaseAction* este clasa utilitara ce ne adauga filmul la filme
cumparate daca avem 2 tokeni sau credite premium pentru filme gratis

- *WatchAction* este clasa utilitara ce ne adauga filmul la filme
vizionate doar daca il avem cumparat

- *LikeAction* este clasa utilitara ce adauga un like filmului curent si
il actualizeaza peste tot cu like-ul adaugat

- *RateAction* este clasa utilitara ce adauga un rating filmului curent si
il actualizeaza peste tot cu ratingul-ul adaugat

   In cazul in care oricare din actiuni nu este permisa in momentul apelarii
din cauza paginii necorespunzaoare de unde este ceruta sau nu se respecta
conditiile pana la capat, se va afisa eroare la output.
___________________________________________________________________________________________________

- Clasa *Main* contine metodele readInput() si writeOutput(), prima preia
date din input si construieste baza de date, iar a doua afiseaza array-ul
de output in fisierele corespunzatoare fiecarui test. Intre apelarile acestor
metode statice in main se apeleaza metoda run() din clasa Executable ce are
rolul de a executa toate actiunile si de a umple array-ul de output.
___________________________________________________________________________________________________

## Feedback :

   -  Din pacate, tema aceasa a fost mai sumar explicata, iar fisierele de ref si
  input puteau fii realizate putin mai bine, avand in vedere ca acestea contineau
  destule greseli sau informatii inutile. Ideea in sine cu platforma de streaming
  e draguta si obiectivele temei cu siguranta sunt atinse. Big up!
___________________________________________________________________________________________________

## Resources:

1. [OCW](https://ocw.cs.pub.ro/courses/poo-ca-cd)
2. [GEEKFORGEEKS](https://www.geeksforgeeks.org/)
3. [STACKOVERFLOW](https://stackoverflow.com/)
