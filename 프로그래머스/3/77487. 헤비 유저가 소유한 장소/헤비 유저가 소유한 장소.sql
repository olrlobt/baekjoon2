-- 코드를 입력하세요
SELECT P.ID, P.NAME, P.HOST_ID
FROM PLACES P
JOIN (
    SELECT HOST_ID
    FROM PLACES
    GROUP BY HOST_ID
    HAVING COUNT(HOST_ID) >= 2
) J
ON P.HOST_ID = J.HOST_ID
ORDER BY P.ID