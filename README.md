# problema

Descriere

Aceasta aplicatie proceseaza un fisier de log-uri care contine inregistrari despre joburi
Scopul este sa identificam durata fiecarui job pe baza timestamp-urilor START si END si sa le afisam pe cele 
care dureaza mai mult de 5 respect 10min

Etape:

    - citirea datelor (preluate ca lista de strings) ; extramen datele relevante : nume, job , start, end , pid
    - parsarea si mapare joburilor - pt fiecare linie extragem datele si construim un obiect JobDTO care stocheza start si end date si este unic prin cheia jobname+PID;
    - filtram in fuctie de calc duratei fiecarui job si le afisam pe cele >5 si 10 min 


Posibile imbunatatiri viitoare

    Exportul  raportului in CSV sau PDF

    Gestionarea cazurilor cand lipsesc date din log, sau sunt linii "corupte"