-- for update in there I need to drop that shit
DROP FUNCTION like_delete_function() cascade;

-- RECREATE FUNCTION
CREATE FUNCTION like_delete_function()
RETURNS TRIGGER AS
$$
BEGIN
UPDATE video SET like_count = like_count-1 WHERE id = OLD.video_id and OLD.like_type = 'LIKE';
RETURN OLD;
END;
$$

LANGUAGE 'plpgsql';


CREATE TRIGGER delete_like
    AFTER DELETE ON video_like
    FOR EACH ROW
    EXECUTE PROCEDURE like_delete_function();
