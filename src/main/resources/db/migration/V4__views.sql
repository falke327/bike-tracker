CREATE OR REPLACE VIEW bike_tracker.v_cost_per_kilometer
AS
SELECT c.cost_year,
       c.yearly_cost,
       t.overall_distance,
       c.yearly_cost / t.overall_distance AS per_kilometer
FROM (SELECT date_part('year'::text, btc.value_date) AS cost_year,
             sum(btc.price)                          AS yearly_cost
      FROM bike_tracker.costs btc
      GROUP BY date_part('year'::text, btc.value_date)) c
         JOIN (SELECT sum(btt.distance)                      AS overall_distance,
                      date_part('year'::text, btt.tour_date) AS tour_year
               FROM bike_tracker.tours btt
               GROUP BY date_part('year'::text, btt.tour_date)) t ON c.cost_year = t.tour_year
ORDER BY c.cost_year;


CREATE OR REPLACE VIEW bike_tracker.v_cost_per_month
AS
SELECT date_part('year'::text, btc.value_date)  AS cost_year,
       date_part('month'::text, btc.value_date) AS cost_month,
       sum(btc.price)                           AS month_costs
FROM bike_tracker.costs btc
GROUP BY date_part('year'::text, btc.value_date),
         date_part('month'::text, btc.value_date)
ORDER BY date_part('year'::text, btc.value_date),
         date_part('month'::text, btc.value_date);


CREATE OR REPLACE VIEW bike_tracker.v_tour_with_bike
AS
SELECT t.tour_date,
       t.distance,
       t.description,
       t.duration,
       t.v_max,
       t.distance * 60::numeric / t.duration::numeric AS v_avg,
       b.name
FROM bike_tracker.tours t
         JOIN bike_tracker.movements m on t.id = m.tour
         Join bike_tracker.bike b ON b.id = m.bike;


CREATE OR REPLACE VIEW bike_tracker.v_distance_overall
AS
SELECT sum(vtwb.distance) AS overall_distance,
       max(vtwb.v_max)    AS highest_v,
       vtwb.name
FROM bike_tracker.v_tour_with_bike vtwb
GROUP BY vtwb.name;


CREATE OR REPLACE VIEW bike_tracker.v_distance_per_month
AS
SELECT date_part('year'::text, vtwb.tour_date)  AS tour_year,
       date_part('month'::text, vtwb.tour_date) AS tour_month,
       sum(vtwb.distance)                       AS overall_distance,
       max(vtwb.v_max)                          AS highest_v,
       vtwb.name
FROM bike_tracker.v_tour_with_bike vtwb
GROUP BY vtwb.name,
         date_part('year'::text, vtwb.tour_date),
         date_part('month'::text, vtwb.tour_date)
ORDER BY date_part('year'::text, vtwb.tour_date),
         date_part('month'::text, vtwb.tour_date),
         vtwb.name;


CREATE OR REPLACE VIEW bike_tracker.v_distance_per_year
AS
SELECT date_part('year'::text, vtwb.tour_date) AS tour_year,
       sum(vtwb.distance)                      AS overall_distance,
       max(vtwb.v_max)                         AS highest_v,
       vtwb.name
FROM bike_tracker.v_tour_with_bike vtwb
GROUP BY vtwb.name,
         date_part('year'::text, vtwb.tour_date)
ORDER BY date_part('year'::text, vtwb.tour_date),
         vtwb.name;
