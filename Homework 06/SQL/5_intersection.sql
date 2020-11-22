/*Are Khan Tuol Kork and Khan Daun Penh intersected?*/
DECLARE @KhanToulKork geography = (SELECT Geo FROM District WHERE DistricID = 1);
DECLARE @KhanDaunPenh geography = (SELECT Geo FROM District WHERE DistricID = 2);
SELECT @KhanToulKork.STIntersects(@KhanDaunPenh) AS INTERSECT_DISTRIC;
