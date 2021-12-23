# MinesweeperHazi

A klasszikus aknakereső játékot újra alkottam.

Egy 10x10-es, véletlenül generált griden 3 módban lehet játszani a játékot: 
-Easy = 10 bomba
-Medium = 20 bomba
-Hard = 30 bomba

Zászlókkal ki lehet jelölni a feltételezett bombák helyét, amik csak zászló módban felvehetők.

Bomba felnyitás esetén, feltárja az egész pályát és egy Game Over feliratot jelenít meg.

A játék megnyerése esetén felugrig egy Dialog Fragment, ahol ha megadja a játékos a nevét felkerül a "Hall of Fame", ahol a játékosok pontszám szerinti sorrenben jelennek meg.
A pontszám a játék módja és megfejtés időtartama alapján számítódik.

A perzistens tároláshoz Room-ot használtam, az játék módját pedig ShardPreferencesben mentettem el.


![Screenshot_1640243658](https://user-images.githubusercontent.com/22506745/147300133-4e39424d-3a3e-4817-9116-090627b63240.png)


![Screenshot_1640243685](https://user-images.githubusercontent.com/22506745/147300137-0c044b03-7920-4687-b141-13e9740841f5.png)

![Screenshot_1640243693](https://user-images.githubusercontent.com/22506745/147300140-a618ac9a-a8fe-4dba-9759-237219424c82.png)



![Screenshot_1640243726](https://user-images.githubusercontent.com/22506745/147300144-c79602a4-4b01-4a10-a060-fecc7aded34c.png)


![Screenshot_1640244243](https://user-images.githubusercontent.com/22506745/147300152-fbcb2848-64f1-4131-be3a-50f2d8109530.png)


![Screenshot_1640244259](https://user-images.githubusercontent.com/22506745/147300156-6a777a3f-95f6-4431-96c7-a16dc74c5b71.png)

![Screenshot_1640243664](https://user-images.githubusercontent.com/22506745/147300162-27dd1c16-18a4-4040-97f5-ec232bd69dae.png)
