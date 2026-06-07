# Agentic Workflow Parser (Java)

Háttérrendszeri (backend) alkalmazás, amely ügynökök (ágensek) munkafolyamat-leíró szövegfájljainak beolvasására, adatvalidálására és szimulált futtatására szolgál. A projekt kiemelt figyelmet fordít a robusztus hibakezelésre és a kód minőségének **JUnit** keretrendszerrel történő biztosítására.

## 🛠️ Alkalmazott technológiák és elvek
* **Nyelv:** Java
* **Tesztelés:** JUnit 5 (Kiterjedt Unit tesztek az üzleti logikára és a kivételkezelésre).
* **Architektúra:** Objektumorientált felépítés, saját egyedi kivételosztály (`WorkflowFormatException`) a hibás formátumú bemenetek transzparens kezelésére.
* Adatszerkezetek (List, HashMap) és Enumok (`SchemaType`) haladó szintű alkalmazása.

## ⚙️ Funkciók
* Workflow leíró `.txt` fájlok beolvasása és szintaktikai elemzése (parsing).
* Szigorú adatvalidáció: duplikált lépésnevek, hiányzó paraméterek és érvénytelen sématípusok szűrése.
* Ágensek lépéseinek szimulációja az elvárt strukturált kimenet (Structured Output) alapján.

## 🧪 Tesztelés
A projekt tartalmazza a szükséges strukturális és funkcionális teszteket. A tesztek futtatásához (Windows környezetben) használd a mellékelt scriptet:
```cmd
check.cmd AgenticWorkflowTestSuite.java AgenticWorkflowTestSuite
