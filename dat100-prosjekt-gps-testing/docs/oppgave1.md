# Oppgave 1: Klasse for GPS datapunkter

I denne oppgaven skal der implementeres en klasse `GPSPoint.java` for å kunne representere GPS datapunkter.

Ideen er at et GPS punkt skal representeres som et objekt av `GPSPoint`-klassen og programmet skal ha et slikt objekt for hvert GPS punkt. For hvert GPS punkt får vi behov for å lagre tidspunkt, breddegrad, lengdegrad og høyde.

*For denne oppgaven er spesielt Kap. 6.1 - 6.3 i Java-boken samt Undervisning 11 og Undervisning 12 relevant.*

### a) Objektvariable og konstruktør

Se på start-koden for klassen `GPSPoint.java` i pakken `no.hvl.dat100ptc.oppgave1`.

Utvid startkoden for klassen slik klassen får følgende objektvariable:

- `time` (heltall) som angir tiden i sekunder
- `latitude` (desimaltall) som angir breddegrad
- `longitude` (desimaltall) som angir lengdegrad
- `elevation` (desimaltall) som angir høyde i meter

Alle objektvariable skal være `private` dvs. kun synlige innenfor klassen.

Videre skal klassen ha en *konstruktør*

```java
public GPSPoint(int time, double latitude, double longitude, double elevation)
```

som kan gi verdi til alle objektvariable.

Test implementasjonen ved å kjøre testene i test-klassen `GPSPointTester.java`.

### b) Hent/sett-metoder

Gjør ferdig implementasjonen av hent/sett (get/set)-metoder og test de med enhetstestene i test-klassen `GPSPointTester.java`.

### c) Representasjon som tekststreng

Gjør ferdig implementasjonen av `toString()`-metoden som returnerer en strengrepresentasjon av et `GPSPoint`-objekt på formen:

`"1 (2.0,3.0) 5.0\n"`

der `1` er tiden, `(2.0,3.0)` er (breddegrad,lengdegrad) og `5.0` er høyden.

Test implementasjonen ved å bruke enhetstestene i test-klassen `GPSPointTester.java`.

### d) Bruke GPSPoint-klassen

Formålet med denne kort oppgaven er å bli kjent med bruken av `GPSPoint`-klassen før neste oppgave.

Implementer en klasse `Main.java` med en main-metode som opprettter et `GPSPoint`-objekt med tid `1`, breddegrad `2.0`, lengdegrad `3.0` og høyde `5.0`.

Bruk `getTime`-metoden for å skrive tidspunkt ut på skjermen og `setTime`-metoden for å endre tidspunkt til `2`. Bruk `toString`-metoden for å skrive informasjon om objektet ut på skjermen.

