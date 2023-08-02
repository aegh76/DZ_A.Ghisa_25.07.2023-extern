package furhatos.app.blankskill1.flow.main.taxi

import Benutzer
import Danke
import FrageWiederholen
import nlu.Ja
import Nein
import WelcherPlatzRaum
import furhatos.app.blankskill1.flow.Parent
import furhatos.app.blankskill1.flow.main.Idle
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.Voice
import furhatos.gestures.Gestures
import furhatos.skills.UserManager.current
import furhatos.util.Language
import furhatsay

//Vergleiche patientdialogue.kt
val Taxidriverdialogue01 : State = state(Parent){
    onEntry {
        val raumx: Any? = Benutzer!!.get("raum")
        val platzy: Any? = Benutzer!!.get("platz")
        val dialyseende: Any? = furhat.voice.sayAs(Benutzer!!.get("dialyseende").toString(), Voice.SayAsType.TIME)
        val dialysebeginn: Any? = furhat.voice.sayAs(Benutzer!!.get("dialysebeginn").toString(), Voice.SayAsType.TIME)

        furhat.say("Ich würde Sie bitten ihren Kunden " +
                "${Benutzer!!.get("name")} im $raumx am $platzy abzuholen")

        //Verzögerung um eine halbe Sekunde, um die Betonung zu optimieren.
        delay(500)

        furhatsay(furhat=this.furhat,
            englishText = "Your client's dialysis starts at $dialysebeginn and ends at" + " $dialyseende I wish you a nice day",
            germanText =  "Die Dialyse ihres Kunden beginnt um $dialysebeginn und endet um" + "$dialyseende ich wünsche Ihnen einen schönen Tag",
            turkishText = "Müşterinizin diyalizi $dialysebeginn'de başlıyor ve" + " $dialyseende'de bitiyor İyi günler dilerim",
            romanianText = "Dializa clientului dumneavoastră începe la $dialysebeginn start și se termină la" + "$dialyseende end Vă doresc o zi bună",
            sprache = Benutzer!!.get("sprache") as Language
        )

        

        furhat.ledStrip.solid(java.awt.Color.GREEN)
        furhat.gesture(Gestures.Nod())
        furhat.gesture(Gestures.BigSmile)
        furhat.listen(timeout = 2000)
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
        furhat.attendNobody()
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
        val raumy: Any? = Benutzer!!.get("raum")
        val platzx: Any? = Benutzer!!.get("platz")
        furhat.say(" In den $raumy, an den $platzx")
        goto(Idle)
    }
}


