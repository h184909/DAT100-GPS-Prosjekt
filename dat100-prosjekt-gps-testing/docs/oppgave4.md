### Oppgave 4: GPS-basert statistikk

I klassen `GPSComputer.java` skal det implementeres metoder som beregner statistikk (nøkkeltall) basert på de innleste og konverterte GPS data.

**Generelt hint:** I forbindelse med implementasjonen bør dere tenke på om der allerede finnes metoder fra tidligere oppgaver eller denne deloppgaven som kan brukes i løsningen. Dette gjelder spesielt metoder fra klassen `GPSUtils.java`?

Klassen `GPSComputer.java` har objektvariablen `gpspoints` som er en tabell med referanser til GPS punkter:

```java
	private GPSPoint[] gpspoints;
```

Konstruktøren i klassen:

```java
public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}
```

er allerede implementert og sørger for (gitt en fil inneholdende GPS punkter) at referansetabellen `gpspoints` inneholder de GPS punktene som utgjør ruten dvs. de punktene som en har syklet igjennom. Metodene som dere skal implementere nedenfor skal bruke data fra denne tabellen til å gjøre beregninger. 

Husk at GPS punkt på posisjon `i` i tabellen kan fåes som `gpspoints[i]`, det etterfølgende punkt som `gpspoints[i+1]`, og at get-metoder på GPS punkt objekt kan brukes til å få den tid og høyde som er lagret i objektet.

Testklassen for oppgaven er `GPSComputerTester.java`.

#### a)

Implementer metoden

```java
public double totalDistance()
```

som beregner den totale distansen (i meter) på ruten som GPS dataene i `gpspoints`-tabellen angir. Dvs. metoden skal legge sammen avstanden (distanser) for etterfølgende punkter på ruten. 

#### b)

Implementer metoden

```java
public double totalElevation()
```

som beregner det totale antall høydemeter (målt i meter) på ruten.

**Husk** kun å telle høydemeter mellom to punkter om en beveger seg oppover.

#### c)

Implementer metoden

```java
public int totalTime()
```

som skal beregne den totale tiden (i sekunder) det har tatt å sykle ruten svarende til de innleste GPS data.

#### d)

Implementer metoden

```java
public double[] speeds()
```

som skal returnere en tabell med gjennomsnitshastigheter målt i m/s mellom hver av de punktene vi har beveget oss mellom. Dvs. første inngang i tabellen skal være hastigheten vi beveget oss med mellom punkt 0 og punkt 1, andre inngang hastigheten mellom punkt 1 og 2 osv. Hvis antall GPS datapunker er *N* da vil lengden av den tabellen som returneres være *N-1*.

#### e)

Implementer metoden

```java
public double maxSpeed()
```

som returnerer den største hastigheten (målt i m/s) vi har beveget oss med mellom to punkter på ruten.

#### f)

Implementer metoden
```java
public double averageSpeed()
```

som returnerer gjennomsnittshastigheten (målt i m/s) vi har beveget oss med total sett for hele ruten.  

#### g)

Implementer metoden

```java
public double kcal(double weight, int secs, double speed)
```

som beregnerer/estimerer hvor mye energi (målt i kcal) syklisten har forbrent gitt vekten på personen og den tid `secs` (i sekunder) som personen har bevæget seg med hastigheten `speed` målt i m/s.

For å kunne estimere energi-forbrenningen i kilo-kalorier (kcal) skal vi først finne *MET* (Metabolic Equivalent of Task) som er et fysiologisk mål for hvor mange kcal vi forbrenner per kilo kroppsvekt per time ved en gitt aktivitet. Når en har beregnet `met` for den gitte hastighen kan kcal altså beregnes som:

```
kcal = met * weight * t 
```

der tiden er målt i timer.

MET avhenger av type aktivitet og intensitet. For sykling [ http://coachlevi.com/health/calories-burned-bicycling/ ] er den gitt i tabellen nedenfor der hastighet er angitt i miles per hour (mps):

```
Hastighet	MET
<10 mph	4.0
10-12 mph	6.0
12-14 mph	8.0
14-16 mph	10.0
16-20 mph	12.0
>20 mph	16.0
```

Hastighet i m/s kan omregnes til mph ved å gange med en faktor *2.23*. I praksis vil MET også avhenge av eks. stigningsprosent (om det går opp eller ned og hvor mye), men det skal vi se bort fra her.

#### h)

Implementer metoden

```java
public double totalKcal(double weight)
```

som beregner den totale energi-mengden som er forbrent på ruten ved å legge sammen kcal for de ulike segmenter på ruten. 

#### i)

Implementer metoden

```java
public void displayStatistics()
```

som skriver ut statistikken som er beregnet av metodene i klassen. Formatet på utskriften skal være slik der vekten til personen er satt til 80 kg.

```
==============================================
Total Time     :   00:36:35
Total distance :      13.74 km
Total elevation:     210.60 m
Max speed      :      47.98 km/t
Average speed  :      22.54 km/t
Energy         :     744.40 kcal
==============================================
```

Husk at distance skal være i km og maksimum samt gjennomsnittshastighet i km/t.

Testene for denne metoden i `GPSComputerTester.java` vil kalle metoden med de fire log-filene og utskriften vises i konsollen i Eclipse slik:

```
==============================================
Total Time     :   01:29:54
Total distance :      25.27 km
Total elevation:     945.50 m
Max speed      :      45.78 km/t
Average speed  :      16.87 km/t
Energy         :     849.60 kcal
==============================================
==============================================
Total Time     :   00:36:35
Total distance :      13.74 km
Total elevation:     210.60 m
Max speed      :      47.98 km/t
Average speed  :      22.54 km/t
Energy         :     436.89 kcal
==============================================
==============================================
Total Time     :   00:27:55
Total distance :       9.19 km
Total elevation:     218.10 m
Max speed      :      36.08 km/t
Average speed  :      19.76 km/t
Energy         :     314.67 kcal
==============================================
==============================================
Total Time     :   02:50:04
Total distance :      46.26 km
Total elevation:     886.00 m
Max speed      :      62.64 km/t
Average speed  :      16.32 km/t
Energy         :    1540.80 kcal
==============================================
```
