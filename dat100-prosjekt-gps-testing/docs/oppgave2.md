# Oppgave 2 - GPS data innlesing og referansetabeller

Denne delen av prosjektet handler om hvordan GPS data kan leses inn fra fil og hvordan GPS punktene i filen kan representeres ved å bruke en tabell med pekere til `GPSPoint`-objekt.

Klassen `GPSDataFileReader.java` inneholder ferdig Java-kode for å lese inn en CSV-datafil med GPS punkter i formatet forklart tidligere. Denne Java-koden er der ikke behov for endre i og det er først litt senere i faget vi skal ta for oss hvordan hvordan en leser fra - og skriver til - filer i Java.

Fokus i denne oppgavene er på å implementere metoder i klassen `GPSData.java` som skal lagre innleste GPS datapunkter i form av en referansetabell av `GPSPoint`-objekt og hjelpeklassen `GPSDataConverter.java` som inneholder hjelpemetoder for å konvertere det data som leses inn til `GPSPoint`-objekt.

*For denne oppgaven er spesielt Kap. 6.4 samt Undervisning 13 relevant.*

#### a) GPS data konvertering (klassen `GPSDataConverter.java`)

Når data leses inn fra fil vil informasjon om GPS punktet være representert som en (tekst)streng. Eksempelvis vil tiden for et innlest GPS punkt være representert som strengen:

```
"2017-08-13T08:52:26.000Z"
```

Gjør ferdig implementasjon av metoden

```java
public static int toSeconds(String timestr)
```

som omregner tidsdata i en tekstreng som representert ovenfor til antall sekunder. For eksemplet ovenfor skal de 8 timer, 52 minutter og 26 regnes om til sekunder omregnes til `31946` sekunder og  returneres.

**Hint:** Se på `substring`-metoden i `String`-klassen samt `parseInt`-metoden i `Integer`-klassen.  

Implementer metoden

```java
public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {
```

som tar String-representasjoner av tid, breddegrad, lengdegrad og høyde, konverterer disse og oppretter et `GPSPoint`-objekt med de tilsvarende data. Metoden skal returnere en peker til det `GPSPoint`-objekt som blev opprettet.

Hvis eksempelvis skal metoden kalles med

```java
convert("2017-08-13T08:52:26.000Z","60.385390","5.217217","61.9")
```

da skal metoden returne et `GPSPoint`-objekt der `time` er `31946`, `latitude` er `60.385390`, `longitude` er `5.217217` og `elevation` er `61.9`.

Test metodene med enhetstestene som finnes i klassen `GPSDataConverterTester.java`.

#### b) Referansetabell med GPS punkter (klassen `GPSData.java)`)

GPS punktene som leses inn fra fil og konverteres skal representeres ved å bruke en referansetabell dvs. en tabell der elementene som er lagret er pekere (referanser) til `GPSPoint`-objekt.

Starten på implementasjonen finnes i klassen `GPSData.java`:

```java
public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

  [...]
```

Objektvariablen `gpspoints` skal brukes til å peke på en referansetabell av GPS punkter. Objektvariabelen `antall` skal brukes ifm. med innsettelse i tabellen til å holde kontroll på hvor (dvs. på hvilken posisjon/indeks) neste GPS-punkt skal settes inn.

Variablen `antall` vil til ethvert tidspunkt angi hvor mange GPS punkter som er satt inn i tabellen. Indeks der det ikke er satt inn noe punkt vil ha verdien `null` (en null-peker). Objektvariablen `antall` har modifikatoren `protected` for å gjøre det enklere å teste klassen.

Testene for klassen finnes i klassen `GPSDataTester.java`.

Gjør ferdig implementasjonen av følgende metoder:

- `public GPSData(int n)` som er en konstruktør for klassen. Konstruktøren skal opprette en referansetabell av GPS punkter med størrelsen gitt ved parameteren `n` og sette `gpspoints` objektvariablen til å peke på denne tabelle. Videre skal metoden sette `antall` lik `0` (siden første element skal inn på posisjon 0).

- `protected boolean insertGPS(GPSPoint gpspoint)` som setter inn GPS punktet `gpspoint` inn i `gpspoints`-tabellen på posisjonen angitt ved objektvariablen `antall`. Videre skal metoden inkrementere `antall` slik neste punkt kommmer inn på neste posisjon. Metoden skal kun sette inn `gpspoint` om der er plass i tabellen dvs. hvis `antall` er strengt mindre enn `gpspoints.length`. Metoden skal returnere `true` om punktet blev satt inn og `false` ellers.

- `public boolean insert(String time, String latitude, String longitude, String elevation)` som tar GPS punkt data i streng-representasjon og setter inn et tilsvarende `GPSPoint`-objekt i `gpspoints`-tabellen.

  **Hint:** Metoden skal konvertere verdiene gitt med som parametre, opprette et nytt `GPSPoint-objekt` og bruke `insertGPS`-metoden ovenfor.

- `public void print()` som skriver ut GPS data som finnes i `gpspoints`-tabellen på følgende formen

```
====== GPS Data - START ======
1 (1.0,2.0) 3.0
2 (4.0,5.0) 6.0
3 (7.0,8.0) 9.0
====== GPS Data - SLUTT ======
```

**Hint**: bruk løkke og `toString`-metoden på `GPSPoint`-objekt.

Metodene som allerede er implementert i klassen `GPSDataFileReader.java` leser - linje for linje - i GPS datafilen og lagrer data i tabellen ved å bruke `insert`-metoden som ble implementert ovenfor.

Dette betyr at punktene i `gpspoint`-tabellen svarer til ruten som er representert i GPS datafilen og `gpspoint`-referansetabellen vil ha samme lengde som antallet av GPS punkter som er leste inn. Det siste betyr videre at hvert element i `gpspoint`-tabellen vil peke på et objekt. dvs. tabellen er full.

#### c) Bruke GPSData-klassen

Formålet med denne oppgaven er å bli mer kjent med bruken av `GPSData`-klassen før de neste oppgavene.

Implementer en klasse `Main.java` med en main-metode som:

- Oppretter to `GPSPoint`-objekter (velg selv argumenter for konstruktøren)

- Oppretter et `GPSData`-objekt med plass til to `GPSPoint`-objekt

- Setter inn de to objektene ved å bruke `insertGPS`-metoden på `GPSData`-objektet.

- Skriver ut informasjon om `GPSPoint`-objektene ved å bruke `print`-metoden på et `GPSData`-objekt.

