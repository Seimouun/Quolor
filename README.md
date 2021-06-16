# Quolor
Want to spice up your parties? Quolor lets you control your Philips Hue lights with ease. From audio visualisation to controlling all of your lights at once with scenes.

## Projekt Plan:
* bis 02.06.2021: Grund Design, API Connection, Methoden um Lichter zu steuern
* bis 09.06.2021: Lichter Anzeigen, Lichter Grafisch Steuern
* bis 16.06.2021: Szenen Hinzufügen, Szenen Setzen
* bis 23.06.2021: Audio Visualization, Fertigstellung

## Rückblick:

* 02.06.2021 -> Grund Design und API Connection keine Probleme, um die Lichter zu steuern braucht man jedoch eine Authentifizierung die noch davor implementiert werden musste. | Arbeitszeit: 2.5H
* 09.06.2021 -> Keine Probleme beim Lichter in einer ListView an zu zeigen, jedoch wird in Philips Hue Mit HSV (Hue, Saturation, Value) gearbeitet und in Android studio mit RGBA. Man kann zwar die HSV werde in RGBA werte konvertieren jedoch verwendet Philips Hue eine andere art von Hue (0-65535). In Android wird mit 0-360° gerechnet. | Arbeitszeit: 4H
* 16.06.2021 -> Szenen implementiert und kleine Bug-fixes. Automatisches speichern von Szenen auf das Device ist jedoch noch nicht möglich. | Arbeitszeit: 3H
* 
