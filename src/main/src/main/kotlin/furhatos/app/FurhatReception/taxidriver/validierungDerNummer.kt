

import furhatos.app.blankskill1.flow.Parent
import furhatos.app.blankskill1.flow.main.Idle
import nlu.Ja
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.Voice
import furhatos.gestures.Gestures
import furhatos.skills.UserManager.current
import furhatos.util.Language

val ValidierungNummerKunde : State = state(Parent) {

    onEntry {
        furhat.attend(users.current)

        val patientennummer = Benutzer!!.get("Patientennummer").toString()
        val patientennummerAsDigits = furhat.voice.sayAs(patientennummer, Voice.SayAsType.DIGITS)

        furhatask(
            furhat=this.furhat,
            englishText = "The patient number is ${patientennummerAsDigits}, is that correct?",
            germanText =  "Die Patientennummer ist ${patientennummerAsDigits}, stimmt das?",
            turkishText = "Hasta numarası ${patientennummerAsDigits}, doğru mu?",
            sprache = Benutzer!!.get("sprache") as Language

        )



        /*/Hier fragt Furhat den gesprächspartner, ob seine verstandene Patientennummer korrekt ist.
        furhat.ask {
            +"Die Patientennummer ist"
            //Durch "Voice.SayAsType.DIGITS" spricht Furhat dabei die Patientennummer als 5 separate Ziffern.
            +voice!!.sayAs("${Benutzer!!.get("Patientennummer")}", Voice.SayAsType.DIGITS)
            +", stimmt das?"
            furhat.gesture(Gestures.BigSmile)
        }*/

    }

    onResponse<Ja> {

        //Die Funktion ReadExcel wird aufgerufen und auf den Benutzer angewendet.
        ReadExcel(Benutzer!!, this.furhat,
            networkDrivePath = "smb://na-filer-w.thm.intern/daten/Projekte/Robotik/Furhat/projekte/Belegungsplan_Dialysezentrum/Furhat.csv",
            password = "FB07Tutor2022@THM", username = "07-Tutor")

        /*  Eingaben fürs DZ
            ReadExcel(Benutzer!!, this.furhat,
            networkDrivePath = "smb://10.203.31.70/mv/furhat/Furhat.csv",
            password = "Furhat2023", username = "Furhat")*/



        //Hat der Benutzer keinen Termin, wird das field "Kein Termin" des Benutzers mit -1 beschrieben und die If-Bedingung
        //trifft zu. Sollte der Benutzer keinen Termin haben wird er darüber informiert, die Interaktion endet dann.
        if (Benutzer!!.get("raum") == null) {
            furhat.say(
                "Sie stehen leider nicht auf dem Belegungsplan welcher mir vorliegt, bitte fragen Sie am" +
                        " Empfang nach. Ich wünsche Ihnen einen schönen Tag"
            )

            furhat.setInputLanguage( Language.GERMAN)
            goto(Idle)
        }
        //Ist das field "row" nicht -1, so läuft die interaktion weiter mit dem State "Patientdialogue".
        else goto(Informationsdialogue)
    }

    onResponse<Nein> {
        furhat.attend(user= current)

        furhat.say(
            "Können Sie mir bitte die Patientennummer eine Nummer nach der anderen aufsagen" +
                    " Danke")
        //Ist die Patientennummer falsch, so wird im State WennNummerFalsch das field patientennummer genullt und
        //Furhat fragt erneut nach der Patientennummer.
        goto(WennNummerFalschPatient)
    }
    onResponse<FrageWiederholen> {
        furhat.attend(user= current)
        reentry()
    }
}




