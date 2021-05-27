# Rockets Project (M9)

Java APP multithread with UI Layout & Jpanels.

Includes power distribution, acceleration, deceleration and cadence control.

<p align="center">

  <img src="https://github.com/gerardpuigl/M9-Java-RocketsProject/blob/master/screenshots/M9-RocketsProject_screenshot.jpg" title="RocketProjectScreenshot" alt="RocketProjectScreenshot" width="700px"/>

</p>

## Exercice Description [in Catalan]

En aquesta pràctica seguiràs amb la POO però la complicaràs afegint una algorítmia i complexitat de negoci elevada. Així mateix tocaràs un concepte important, sincronia asincronia i la forma de ferles servir, els threads.

<details>
  <summary><strong>Nivell 1</strong></summary>
  <p>
  
  - Fase 1
  Volem fer un software de carreres de coets. Un coet està identificat per un codi de 8 caràcters i un número de propulsors.  

  Realitza els següents passos: 

  Creem dos coets amb els codis “32WESSDS” i “LDSFJA32”. El primer coet tindrà tres propulsors i el segon sis propulsors. 
  Mostrar a pantalla el codi dels coets i el número de propulsors que té. 

  - Fase 2
  Volem millorar el software perquè el propulsor tingui una potència màxima. 

  Modifiquem en el main anterior:  

  Creem dos coets amb els codis “32WESSDS” i “LDSFJA32”. El primer coet tindrà tres propulsors (potència: 10,30,80) i el segon sis propulsors (potència: 30,40,50,50,30,10).  
  Mostrar a pantalla el codi dels coets, el número de propulsors que té i la potència màxima de cada propulsor.  

  - Fase 3
  Per la fase 3 modificarem els propulsors afegint una potencia actual i una potencia objectiu. Un propulsor tindrà una potència màxima (no la pot superar), una potencia actual (la potencia que té el propulsor en aquell moment) i una potencia objectiu que ha d'assolir sense superar la potència màxima.  Tots els propulsors tindran una potència actual que començarà amb 0.  

  El coet tindrà dos mètodes, accelerar o frenar, els quals ajudaran a assolir la potència objectiu de cada propulsor. Cada propulsor ha de saber si ha d'augmentar o baixar d'un en un la seva potencia i de forma independent (threads!). 

  Com a resultat hauries de veure per pantalla cada propulsor amb el fil adequat pujant o baixant la potencia segons hagis indicat a l'ordre d'accelerar o frenar 
  </p>
</details>

<details>
  <summary><strong>Nivell 2</strong></summary>
  <p>
  
 - FASE D’ALGORITMIA
Ara suposem que el coet està volant a l’espai sense fricció. La velocitat la podem calcular de la següent manera: 

v = vo + 100 x SQR(power)

vo: és la velocitat inicial (la velocitat a la que anava abans de fer aquest càlcul) 

PT: és la suma de la potencia de cada propulsor 

Donada una velocitat inicial, s'ha de calcular la Potencia total necessària, repartir-la entre els coets (tenint en compte el seu màxim) i accelerar d'acord amb això, de no poder accelerar fins a la velocitat indicada per falta de potència s'ha d'informar per pantalla. 
 
  </p>
</details>

<details>
  <summary><strong>Nivell 3</strong></summary>
  <p>
  
Ara volem poder interrompre i modificar la velocitat del cohet. Per això necessitem que la visualització de les dades no interrompi la introducció de les ordres. Afegeix una interfície gràfica de les dades i ordres (botons) utilitzant Jpanel. 

Has de poder modificar la velocitat objectiu interrompent l'acceleració del cohet. És a dir en meitat d'una acceleració o frenada has de poder modificar-ho (interrupció de threads, missatges quan el thread acaba, etc…) 

També has de poder modificar la velocitat d'acceleració, és a dir en comptes d'accelerar d'un en un poder modificar-lo a 5 en 5 o el que vulguis.

Tot ha de ser possible fer-ho en meitat d'una acceleració o frenada dels cohets. 

  </p>
</details>
