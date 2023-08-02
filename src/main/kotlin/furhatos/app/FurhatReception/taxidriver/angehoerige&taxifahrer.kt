
import furhatos.app.blankskill1.flow.Parent
import furhatos.app.blankskill1.flow.main.Idle
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.skills.UserManager.current
import furhatos.util.Language
import nlu.Ja



val AngehoerigeUndTaxifahrer : State = state(Parent) {
    onEntry {


        furhatask(
            furhat=this.furhat,
            englishText = "Are you here to bring someone or get someone?",
            germanText =  "Sind Sie hier, um Jemanden zu bringen beziehungsweise zu holen?",
            turkishText = "Buraya birini getirmeye mi yoksa almaya mı geldiniz?" ,
            romanianText = "Ați venit să aduceți sau să aduceți pe cineva?",
            sprache = Benutzer!!.get("sprache") as Language)


    }
    onResponse<Ja> {

        //Nun wird die Variable Benutzer mit dem User überschrieben, der auf die Frage geantwortet hat.
        Benutzer = users.getUser(it.userId)

        //Der Benutzer wird von Furhat angeschaut
        furhat.attend(user= current)

        //Mit der Funktion GetDigitsPatient wird die Frage nach der Patientennummer des Gesprächspartners gestellt.
        //User kann nicht null sein deswegen können wir schreiben Benutzer!!, da der Benutzer bereits gesetzt ist.
        GetDigitsKunde(Benutzer!!, this.furhat)
    }
    onResponse<Nein> {


        furhatsay(
            furhat=this.furhat,
            englishText = "Then please report to the front desk" + this.furhat.gesture(Gestures.Nod) + "Thank you.",
            germanText =  "Dann melden Sie sich bitte vorne beim Empfang" + this.furhat.gesture(Gestures.Nod) + "Danke",
            turkishText = "O zaman lütfen ön büroya rapor verin" + this.furhat.gesture(Gestures.Nod) + "Teşekkür ederim",
            romanianText = "Atunci vă rugăm să vă prezentați la recepție" + this.furhat.gesture(Gestures.Nod) + "Mulțumesc",
            sprache = Benutzer!!.get("sprache") as Language)

            delay(2000)
            goto(Idle)
        }


}



