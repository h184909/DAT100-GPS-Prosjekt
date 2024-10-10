# Oppgave 0: Eclipse-prosjekt med startkode og enhetstester

## Startkode og enhetstester

Første steg for å komme igang med programmeringen er å laste ned et Eclipse-prosjekt med start-kode og et Eclipse-prosjekt med enhetstester og importere disse.

Det ene Eclipse-prosjekt inneholder en rekke Java-klasser som utgjør rammen rundt en sykkelcomputer-applikasjonen. Dere trenger ikke å legge til nye klasser, men dere skal implementere ulike metoder i klassene som er gitt.

Det andre Eclipse-prosjektet inneholder en rekke enhetstester som skal bruke til automatisk å teste den koden dere skriver.

Fremgangsmåten for å importere Java-koden inn i Eclipse er beskrevet nedenfor og også vist i en video som er lagt ut på Canvas for programmeringsprosjektet. Gremgangmåten er den samme som dere har brukt tidlige.

## Steg 1: Importere Eclipse-prosjekt med startkode 

### Opprette egen kopi av oppbevaringsplass for startkode (Use as template) 

Gå inn på github på oppbevaringsplassen som inneholder start-koden:

https://github.com/dat100hib/dat100-prosjekt-gps-startkode

Begynn med å opprette en kopi på egen konto av denne oppbevaringsplassen ved å bruke **Use as template** (se øverste høyre hjørne på github-websiden). Du vil da få en kopi av oppbevaringsplassen med startkoden på din egen github konto. Kun en i gruppen skal gjøre dette (se nedenfor).

### Klone og importere startkode i Eclipse (Clone og Import)

Du må nå **klone** (laste ned) oppbevaringsplassen med kode som du opprettet ovenfor. 

Det er **viktig** at du **ikke** kloner https://github.com/dat100hib/dat100-prosjekt-gps-startkode, men kloner den oppbevaringsplassen som du opprettet på din egen konto da du utførte *use as template* ovenfor.

1.	Velg **<>Code** på github-siden for oppbevaringsplassen og kopier lenken.

2.	Gå inn i Github Desktop og velg *File -> Clone Repository ...*, lim inn lenken under *URL* og velg hvor du vil lagre din lokale kopi av oppbevaingsplassen. Velg deretter *Clone*

3. Importer prosjektet inn i Eclipse ved å bruke *File -> Import ->Existing projects into Workspace*

Du skal nå ha et prosjekt i Eclipse med navnet `dat100-prosjekt-gps-startcode`

Eclipse-prosjektet er organisert i et antall pakker med en pakke til hver av oppgavene i prosjektet. Pakkene inneholder de klasser og metoder som gruppen skal implementere i oppgavene som presenteres nedenfor.

De steder i koden hvor der skal implementeres Java-kode er merket med en kommenter og teksten `TODO`. Disse plassene i koden kan lett identifiseres ved at de gir en blå markering i høyresiden av editor-vinduet i Eclipse.

De metoder som skal implementeres inneholder en linje på formen:

```java
throw new UnsupportedOperationException(TODO.method(" ... "));
```
som signaler et *unntak* om at en metode/konstruktør ikke er implementert. Denne linjen skal dere fjerne når metoden implementeres.

For å gjøre det enklere ifm. presentasjon av oppgaven å finne de plassene hvor dere har lagt til kode, anbefales det å la kommentarene med `TODO – START` og `TODO SLUTT` bli stående i koden.  
 
## Steg 2: Eclipse-prosjekt med enhetstester (Clone + Import)

Formålet med enhetstestene er å gjøre det enklere å teste metoder etterhvert som de implementeres uten å skulle starte selve sykkecomputer-applikasjonen. 

Et Eclipse-prosjekt med ferdige enhetstester finnes på følgende github oppbevaringsplass:

https://github.com/dat100hib/dat100-prosjekt-gps-testing 

Du **kloner** oppbevaringsplassen med enhetstest og importerer det inn i Eclipse på samme måten som tidligere, men nå med oppbevaringsplassen gitt i lenken ovenfor. Du trenger **ikke** å gjøre *Use as template* på denne oppbevaringsplassen først siden du ikke trenger din egen kopi av oppbevaringsplassen på github.

Konvensjonen er at enhetstester for en klasse `X.java` er implementert i filen med navn `XTester`.java. Eksempelvis inneholder klassen `GPSUtilsTester.java` enhetstester for klassen `GPSUtils.java` hvor dere skal implementere metoder.

## Utføre JUnit enhetstest

En test-klasse med enhetstest utføres ved å velge test-klassen etterfulgt av *Run As → JUnit Test*. Man kan kjøre alle testene i en test-pakke ved å velge pakken etterfulgt av *Run As → JUnit Test*. Alle testene i Eclipse-prosjektet kjøres ved å velge prosjektet etterfulgt av *Run As → JUnit Test*.

Det er god/anbefalt praksis å gjenta testene når det gjøres endringer/forbedringer i implementasjonen av en metode. Det anbefales også å teste etterhvert som de enkelte metoder implementeres. Enhetstestene er ikke komplette, men tester basale ting for metodene i de klasser som dere skal implementere.

Enhetstesting og test-drevet utvikling er et tema senere i studiet og det er et generelt godt ingeniørprinsipp å teste komponentene sine før de settes sammen til et større system.

I mappen `logs` i Eclipse-prosjektet finnes tre CSV filer filer: `short.log`, `medium.log`, `long.log` og `vm.log` med GPS data punkter som kan brukes som input til Java sykkelcomputeren.

## Dele oppbevaringsplass mellom gruppemedlemmer

Det anbefales at gruppen bruker en felles github-oppbevaringsplass for koden som utvikles. Det er derfor tilstrekkelig at en i gruppen utfører steg 1 ovenfor og gir de andre medlemmene i gruppen tilgang til oppbevaringsplassen (repository). Dette kan gjøres ved å logge inn på https://www.github.com, gå til oppbevaringsplassen og via *Settings* og *Collaborators and Teams* for oppbevaringsplassen legge til de andre medlemmer i gruppen som *Collaborators*.

De andre gruppemedlemmene må da klone oppbevaringsplassen ned på egen PC via Github Desktop og importere prosjektet inn i Eclipse.

Endringer i filer i prosjektet lastes opp til den felles oppbevaringsplassen ved å bruke *Commit* og *Push to origin* i Github Desktop applikasjonen.

For å redusere konflikter ifm. endringer i koden bør du utføre *Fetch from origin* og *pull* før du begynner å gjøre endringer og det er god praksis ofte å  gjøre commit og push opp til opbevaringsplassen slik endringer de blir tilgjengelig for de andre gruppemedlemmer.

På Canvas finnes en video som forklarer i mer detalj hvordan en skal gå frem for å flette (merge) endringer om to gruppemedlemmer har redigert i samme den samme koden samtidig - noe som potensielt kan lede til endringer er i konflikt med hverandre og som evt. må løses.