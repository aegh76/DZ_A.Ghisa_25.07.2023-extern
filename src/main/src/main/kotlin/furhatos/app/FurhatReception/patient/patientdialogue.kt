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
        var dialysebeginn: Any? = furhat.voice.sayAs(Benutzer!!.get("dialysebeginn").toString(), Voice.SayAsType.TIME)
        var dialyseende: Any? = furhat.voice.sayAs(Benutzer!!.get("dialyseende").toString(), Voice.SayAsType.TIME)


        val currentDateTime = LocalDateTime.now(ZoneId.of("Europe/Berlin"))
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        val formattedDateTime = currentDateTime.format(formatter)

        furhat.say("Aktuelle Uhrzeit und Datum: $formattedDateTime")





        //Der Nutzer wird über seine Termindaten informiert und weiß somit, wann, wo und wie lange seine Dialyse
        //stattfinden wird.
        furhat.say (
            "Gut, ${Benutzer!!.get("name")}. Ich würde Sie ${furhat.voice.emphasis("bittten")} in " +
                    "den${furhat.voice.emphasis("$raumy")} an den  PLatz ${furhat.voice.pause("1000ms")} + ${furhat.voice.emphasis("$platzx")} " +
                    "zu gehen. Ihre Dialyse fängt um $dialysebeginn an und endet um $dialyseende"
        )
        furhat.say("Ich hoffe ich konnte Ihnen helfen, einen ${furhat.voice.emphasis("schönen")} Tag noch")
        furhat.gesture(Gestures.Nod())
        furhat.gesture(Gestures.BigSmile)

        //Für 8 Sekunden hört Furhat dann seinem Gesprächspartner zu, falls noch Fragen bezüglich der Platzinformation
        //offen sind, kann furhat die Informationen nochmal wiederholen. Der State wird dann nicht nochmal von vorne
        //begonnen, sondern startet bei onReentry (Zeile 65).
        furhat.listen(timeout = 8000)
        furhat.setInputLanguage( Language.GERMAN)
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
        furhat.say("Ich danke ebenfalls, es war mir eine Freude")
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
        var raumy: Any? = Benutzer!!.get("raum")
        var platzx: Any? = Benutzer!!.get("platz")
        var dialysebeginn: Any? = furhat.voice.sayAs(Benutzer!!.get("dialysebeginn").toString(), Voice.SayAsType.TIME)
        furhat.say(" Ihre Dialyse beginnt um $dialysebeginn im $raumy, am $platzx")
        delay(5000)
        goto(Idle)
    }
}


