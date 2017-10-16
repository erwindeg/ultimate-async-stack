#Introductie
Ontwikkelaars krijgen steeds meer tools aangereikt om asynchrone afhandeling van applicatielogica mogelijk te maken. Asynchrone programmalogica biedt voordelen t.o.v. synchrone code, omdat er efficiënter gebruik kan worden gemaakt van de beschikbare resources. Zo kun je bijvoorbeeld een database query uitvoeren en terwijl je wacht op het resultaat een webservice request kunnen uitvoeren. Er zijn verschillende mogelijkheden om asynchrone code te schrijven. Voor javascript ontwikkelaars is het bijvoorbeeld vanzelfsprekend om gebruik te maken van callbacks. Sinds versie 8 is deze mogelijkheid er ook in Java in de vorm van Lambda statements. Ook kan er gebruik worden gemaakt van multi-threading, van patterns zoals een event-loop, van co-routines (nodejs, kotlin) en van libraries als reactive extensions (RxJava, RxJS). Asynchrone afhandeling is vooral voordelig als we te maken hebben met interactie tussen applicaties. Het meest populaire protocol hiervoor is echter REST. Gebaseerd op HTTP is dit een synchroon protocol. 
In dit artikel kijken we aan de hand van een voorbeeld naar een alternatief in de vorm van websockets. We zullen een systeem bouwen met een Angular frontend en een Vert.x backend. Voor de asynchrone afhandeling maken gebruik van Observables in RxJS en RxJava. Het voorbeeld gebruikt websockets omdat dit een populaire standaard is. Het principe kan echter ook worden toegepast op vergelijkbare protocollen zoals bijvoorbeeld TCP.

#Use case

#Technologie

#Frontend

#Backend

#Conclusie
