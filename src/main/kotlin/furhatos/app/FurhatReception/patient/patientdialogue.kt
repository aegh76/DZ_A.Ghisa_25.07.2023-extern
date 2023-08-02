import nlu.Ja
import furhatos.app.blankskill1.flow.Parent
import furhatos.app.blankskill1.flow.main.Idle
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.Voice
import furhatos.gestures.Gestures
import furhatos.skills.UserManager.current
import furhatos.util.Language
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

val Informationsdialogue : State = state(Parent) {

    onEntry {

        //Zunächst werden die Werte raumy, platzx, dialysebeginn und dialyseende mit den fields raum, platz,
        //dialysebeginn und dialyseende des jeweiligen Patienten gesetzt und entsprechend manipuliert.
        val raumy: Any? = Benutzer!!.get("raum")
        val platzx: Any? = Benutzer!!.get("platz")
        val dialysebeginn: Any? = furhat.voice.sayAs(Benutzer!!.get("dialysebeginn").toString(), Voice.SayAsType.TIME)
        val dialyseende: Any? = furhat.voice.sayAs(Benutzer!!.get("dialyseende").toString(), Voice.SayAsType.TIME)
        val datum = furhat.voice.sayAs(Benutzer!!.get("datum").toString(), Voice.SayAsType.TIME)


        val currentDateTime = LocalDateTime.now(ZoneId.of("Europe/Berlin"))
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        val formattedDateTime = currentDateTime.format(formatter)




        //Der Nutzer wird über seine Termindaten informiert und weiß somit, wann, wo und wie lange seine Dialyse
        //stattfinden wird.


        furhatsay(furhat=this.furhat,
            englishText = "Alright, ${Benutzer!!.get("name")}. ${furhat.voice.emphasis("please")} go to " +
                    "the room${furhat.voice.emphasis("$raumy")} ${furhat.voice.pause("1200ms") } and bed number ${furhat.voice.emphasis("$platzx")} ${furhat.voice.pause("1200ms") } . Your dialysis will start at ${furhat.voice.emphasis("$dialysebeginn")}  and end at ${furhat.voice.emphasis("$dialyseende")} on $datum",
            germanText =  "Gut, ${Benutzer!!.get("name")}. Ich würde Sie ${furhat.voice.emphasis("bittten")} in " +
                    "den Raum${furhat.voice.emphasis("$raumy")} ${furhat.voice.pause("1200ms") } an den  Platz ${furhat.voice.emphasis("$platzx")} ${furhat.voice.pause("1200ms") }" +
                    "zu gehen. Ihre Dialyse fängt um ${furhat.voice.emphasis("$dialysebeginn")} an und endet um ${furhat.voice.emphasis("$dialyseende")} am $datum",
            turkishText = "Şey, ${Benutzer!!.get("isim")}. Ben ${furhat.voice.emphasis("sor")}'ı oda" +
                    "${furhat.voice.emphasis("$raumy")} ${furhat.voice.pause("1200ms") } Yer'a ${furhat.voice.emphasis("$platzx")} ${furhat.voice.pause("1200ms") }" +
                    "gitmek için. Diyaliziniz ${furhat.voice.emphasis("$dialysebeginn")} başlangıcında başlayacak ve $datum ${furhat.voice.emphasis("$dialyseende")} bitişinde sona erecektir",
            romanianText = "Bine ${Benutzer!!.get("name")}., v-aș ruga să mergeți în camera. ${furhat.voice.emphasis("$raumy")}  " +
                    "${furhat.voice.pause("1000ms") },la locul ${furhat.voice.emphasis("$platzx")}. Dializa dumneavoastră începe la $dialysebeginn  și se termină la $dialyseende la $datum",
            sprache = Benutzer!!.get("sprache") as Language)

        delay(1500)

        furhatsay(furhat=this.furhat,
            englishText = "the current time and date is: $formattedDateTime",
            germanText =  "Aktuelle Uhrzeit und Datum ist: $formattedDateTime",
            turkishText = "Geçerli saat ve tarih: $formattedDateTime",
            romanianText = "Data și ora curentă: $formattedDateTime",
            sprache = Benutzer!!.get("sprache") as Language)

        delay(1500)

        furhatsay(furhat=this.furhat,
            englishText = "I hope I could help you have a ${furhat.voice.emphasis("beautiful")} day",
            germanText =  "Ich hoffe ich konnte Ihnen helfen, einen ${furhat.voice.emphasis("schönen")} Tag noch",
            turkishText = "Umarım bir ${furhat.voice.emphasis("güzel")} sahibi olmanıza yardımcı olabilirim. gün henüz",
            romanianText = "Sper că v-aș putea ajuta să aveți inca o zi ${furhat.voice.emphasis("frumoasă")} ",
            sprache = Benutzer!!.get("sprache") as Language)

        furhat.gesture(Gestures.Nod())
        furhat.gesture(Gestures.BigSmile)

        //Für 8 Sekunden hört Furhat dann seinem Gesprächspartner zu, falls noch Fragen bezüglich der Platzinformation
        //offen sind, kann furhat die Informationen nochmal wiederholen. Der State wird dann nicht nochmal von vorne
        //begonnen, sondern startet bei onReentry (Zeile 65).
        furhat.listen(timeout = 8000)

    }
    onResponse<FrageWiederholen> {
        furhat.attend(user= current)
        reentry()
    }

    onResponse<WelcherPlatzRaum> {
        furhat.attend(user= current)
        reentry()
    }
    onNoResponse {
        delay(6000)
        goto(Idle)
    }
    onResponse<Danke> {
        furhatsay(furhat=this.furhat,
            englishText = "Thank you as well, it was a pleasure",
            germanText =  "Ich danke ebenfalls, es war mir eine Freude",
            turkishText = "Ben de teşekkür ederim, benim için bir zevkti",
            romanianText = "Și eu vă mulțumesc, a fost o plăcere",
            sprache = Benutzer!!.get("sprache") as Language)

        delay(4000)
        goto(Idle)
    }
    onResponse<Ja> {
        goto(Idle)
    }
    onResponse<Nein> {
        goto(Idle)
    }
    onReentry {
        //Falls diese wiederholt werden müssen, teilt Furhat dem Gesprächspartner noch einmal die relevanten Daten mit.
        val raumy: Any? = Benutzer!!.get("raum")
        val platzx: Any? = Benutzer!!.get("platz")
        val dialysebeginn: Any? = furhat.voice.sayAs(Benutzer!!.get("dialysebeginn").toString(), Voice.SayAsType.TIME)


        furhatsay(furhat=this.furhat,
            englishText = " Your dialysis will start at $dialysebeginn start in room $raumy, bed $platzx",
            germanText =  " Ihre Dialyse beginnt um $dialysebeginn im Raum $raumy, am platz $platzx",
            turkishText = "Diyaliziniz oda $raumy'de, yer $platzx'de $dialysebeginn başlangıcında başlayacaktır",
            romanianText = "Dializa dumneavoastră va începe la $dialysebeginn in camera $raumy, la locul $platzx",
            sprache = Benutzer!!.get("sprache") as Language)


        delay(5000)
        goto(Idle)
    }
}


