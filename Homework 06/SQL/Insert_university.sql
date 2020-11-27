DECLARE @g geography;
SET @g = geography::STGeomFromText(
'POLYGON((
104.8870771 11.5573152,
104.887058 11.5573198,
104.8869253 11.5573518,
104.8865782 11.5574353,
104.8865617 11.5574393,
104.8865436 11.5573673,
104.8863872 11.5573882,
104.8864002 11.5574951,
104.8862572 11.5575108,
104.8862669 11.5577758,
104.8864637 11.5577584,
104.8864604 11.5577217,
104.8866239 11.5576992,
104.8871579 11.55754,
104.8870771 11.5573152))',4326);
INSERT INTO University(UniName, Geo) VALUES ('Cambodian Mekong University', @g);
SELECT @g;

DECLARE @g1 geography;
SET @g1 = geography::STGeomFromText(
'POLYGON((
104.9021383 11.5669577,
104.9030135 11.5670488,
104.9030169 11.5670152,
104.9030412 11.5667835,
104.9028598 11.5667634,
104.9022178 11.5666884,
104.902189 11.5666936,
104.9021642 11.5667179,
104.9021383 11.5669577))',4326);
INSERT INTO University(UniName, Geo) VALUES ('Economic and Finance Institute', @g1);
SELECT @g1;

DECLARE @g2 geography;
SET @g2 = geography::STGeomFromText(
'POLYGON((
104.8994598 11.5713178,
104.8998161 11.5702625,
104.8998378 11.5701904,
104.8998606 11.5701225,
104.8997955 11.5699179,
104.8996819 11.5698658,
104.89821 11.569688,
104.8978068 11.5696008,
104.8970409 11.5693948,
104.8964553 11.5691799,
104.8962261 11.5709731,
104.8994123 11.5713563,
104.8994598 11.5713178))',4326);
INSERT INTO University(UniName, Geo) VALUES ('Institute of Technology of Cambodia', @g2);
SELECT @g2;

DECLARE @g3 geography;
SET @g3 = geography::STGeomFromText(
'POLYGON((
104.8906518 11.5730538,
104.8903985 11.5732642,
104.8902438 11.5730854,
104.8904971 11.572875,
104.8906518 11.5730538))',4326);
INSERT INTO University(UniName, Geo) VALUES ('Paññāsāstra University of Cambodia', @g3);
SELECT @g3;

DECLARE @g4 geography;
SET @g4 = geography::STGeomFromText(
'POLYGON((
104.9109546 11.5638325,
104.9109499 11.5633283,
104.911607 11.5633322,
104.9116053 11.563516,
104.9116774 11.5635173,
104.9116626 11.5636014,
104.9116344 11.5636553,
104.9116035 11.5637001,
104.9116271 11.5637106,
104.9116451 11.5637251,
104.9116565 11.5637416,
104.9116618 11.563763,
104.9116596 11.5637936,
104.9116496 11.563813,
104.9116362 11.5638301,
104.911618 11.5638422,
104.9115979 11.5638479,
104.911577 11.5638515,
104.9115566 11.5638485,
104.9115409 11.5638427,
104.9115266 11.5638313,
104.9109546 11.5638325))',4326);
INSERT INTO University(UniName, Geo) VALUES ('Phnom Penh International University', @g4);
SELECT @g4;

DECLARE @g5 geography;
SET @g5 = geography::STGeomFromText(
'POLYGON((
104.9246406 11.5381498,
104.9246378 11.537265,
104.9254996 11.5372655,
104.9255051 11.5381561,
104.9253827 11.5381497,
104.9246406 11.5381498))',4326);
INSERT INTO University(UniName, Geo) VALUES ('Royal School of Administration', @g5);
SELECT @g5;

DECLARE @g6 geography;
SET @g6 = geography::STGeomFromText(
'POLYGON((
104.8771295 11.5890796,
104.877263 11.5865704,
104.8761818 11.5865176,
104.8761188 11.5872785,
104.8757529 11.5872418,
104.8757498 11.5873041,
104.8756749 11.5888137,
104.8760888 11.5888298,
104.8767171 11.58899,
104.8769796 11.5890473,
104.8769916 11.5890076,
104.8771295 11.5890796))',4326);
INSERT INTO University(UniName, Geo) VALUES ('Royal University of Fine Arts', @g6);
SELECT @g6;

DECLARE @g7 geography;
SET @g7 = geography::STGeomFromText(
'POLYGON((
104.9237012 11.5363415,
104.9246236 11.5362972,
104.9251734 11.5362915,
104.9258046 11.5362377,
104.9257949 11.5355685,
104.9258104 11.5354851,
104.9238547 11.5352129,
104.9237012 11.5363415))',4326);
INSERT INTO University(UniName, Geo) VALUES ('Royal University of Laws and Economics', @g7);
SELECT @g7;

DECLARE @g8 geography;
SET @g8 = geography::STGeomFromText(
'POLYGON((
104.8882184 11.5700125,
104.8883652 11.5684816,
104.8885188 11.5682962,
104.8886124 11.5680884,
104.8886437 11.5678702,
104.8885836 11.5676691,
104.8884647 11.5674445,
104.8885601 11.5665205,
104.8886912 11.5663577,
104.8889409 11.5662525,
104.8894072 11.5665905,
104.8896639 11.5667496,
104.8901304 11.5669897,
104.8903341 11.5670629,
104.8910343 11.567258,
104.8915233 11.567423,
104.8916496 11.5674678,
104.8940781 11.5683571,
104.8939137 11.5696361,
104.8938414 11.5701648,
104.8937777 11.5706784,
104.8882184 11.5700125))',4326);
INSERT INTO University(UniName, Geo) VALUES ('Royal University of Phnom Penh', @g8);
SELECT @g8;

DECLARE @g9 geography;
SET @g9 = geography::STGeomFromText(
'POLYGON((
104.9059935 11.5434248,
104.9060875 11.5434018,
104.9061815 11.5433787,
104.9065516 11.5432145,
104.90662 11.5432026,
104.9066924 11.5432092,
104.9072133 11.5438579,
104.9072061 11.5439096,
104.9063692 11.544719,
104.9063315 11.5447199,
104.9062544 11.5446368,
104.90574 11.5440349,
104.9061882 11.5436717,
104.9059935 11.5434248))',4326);
INSERT INTO University(UniName, Geo) VALUES ('University of Health Sciences', @g9);
SELECT @g9;

DECLARE @g10 geography;
SET @g10 = geography::STGeomFromText(
'POLYGON((
104.9213414 11.5626244,
104.9212952 11.5629849,
104.9201967 11.5628285,
104.9202454 11.5625003,
104.9203017 11.5621206,
104.9204981 11.5621486,
104.9204415 11.5624928,
104.9213414 11.5626244))',4326);
INSERT INTO University(UniName, Geo) VALUES ('University of Puthisastra', @g10);
SELECT @g10;