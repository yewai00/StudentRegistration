DELIMITER //

CREATE PROCEDURE get_free_one_course_students()
BEGIN
    SELECT s.*
    FROM students s
    WHERE s.id IN (
        SELECT s2.id
        FROM students s2
        JOIN student_course sc ON s2.id = sc.student_id
        GROUP BY s2.id
        HAVING COUNT(sc.course_id) > 5
    );
END //

DELIMITER ;