-- 코드를 작성해주세요

SELECT II2.ITEM_ID, II2.ITEM_NAME, II2.RARITY
FROM ITEM_TREE IT2
JOIN ITEM_INFO II2
ON IT2.ITEM_ID = II2.ITEM_ID

JOIN (
    SELECT IT1.ITEM_ID
    FROM ITEM_TREE IT1
    JOIN ITEM_INFO II1
    ON IT1.ITEM_ID = II1.ITEM_ID AND II1.RARITY = "RARE"
) J1
ON J1.ITEM_ID = IT2.PARENT_ITEM_ID

ORDER BY II2.ITEM_ID DESC