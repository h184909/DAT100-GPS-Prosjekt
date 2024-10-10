# Oppgave 6: Ekstraoppgaver

Disse er frivillige og tenkt som ekstra utfordringer for de som har lyst på det.

### a) Ekstraoppgaver for oppgave 4

Utvid klassen `GPSComputer.java` med en metode

```java
public double[] climbs()
```

som beregner stigningsprosent for de ulike deler av ruten og en metode

```java
public double maxClimb()
```

som finner den del av ruten som har den høyeste stigningsprosent.

### b) Ekstraoppgaver for oppgave 5

Utvid oppgave for visualisering med tekststrenger som viser enheter på y-aksen.

Utvid `showHeightProfile`-metoden slik kurven kan tegnes i skalert real-tid. Husk at tabellen med GPS punkter gir tidspunkt for hvert datapunkt og vi kan utefra dette finne den reelle tiden mellom to punkter. For at det ikke skal ta for lang tid å tegne høydeprofilen skal brukeren kunne taste inn et tall som angir hvor mye tiden skal skaleres ned. Eksempelvis vil tallet 100 angi at hvert sekund i realtid skal tilsvare til 1/100 sekund når datafilen visualiseres.

Skaleringsfaktoren kan leses inn ved å bruke `getText`-metoden i EasyGraphics og metoden `pause` (også fra EasyGraphics) kan brukes til å implementere en pause mellom tegning av hver søyle.

Forbind punktene i ruten som visualieres med linjer. Få en blå sirkel til å flytte seg langs ruten der hastigheten som sirkelen flytter seg med mellom to punkter er relativt til den hastighet som det blev kjørt med på denne delen av sykkelturen. EasyGraphics-metoden `speed` kan brukes til å kontrollere hastigheten som grafiskobjekter blir flyttet med.

### c) En samlet sykkelcomputer

Gjør ferdig implementasjonen av klassen `CycleComputer.java` som utvider og samler al visualisering fra de forrige oppgavene i et vindu. Videre skal programmet løpende (i skalert realtid) vise tid, aktuell hastighet, gjennomsnitt osv. (øverst til venstre) og visualisere høydeprofil og tegne ruten på et kart etter hvert vi kommer frem til de enkelte GPS posisjoner. Figuren nedenfor skisserer ideen:

![](assets/markdown-img-paste-20180909131911330.png)

### d) Flere samtidige sykkelryttere

Utvid programmet slik det kan håndtere to (eller flere) samtidige sykkelryttere (representert ved hver sin GPS datafil) som sykler samme ruten.

### e) Kom i mål før din underviser

Denne oppgaven er vanskelig :-)

Last ned en GPS Logger app’en på mobilen din som kan logge og dele GPS data i CSV format.

Sett deg på sykkelen og kjør samme ruten som i GPS datafilen vm-logfilen fra prosjektet - se eksempelvis ruten på https://gpx.pelmers.com/

Bruk programmet fra Oppgave 6d) til å undersøke om du sykler raskere enn din underviser.
