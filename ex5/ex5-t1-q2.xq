for $cinema in doc("ex5-t1.xml")//cinema
where some $movie in $cinema/movies/title satisfies $movie/year = 1949
return <moviesform1949>
    <CinemaName>{string($cinema/cinemaname)}</CinemaName>
</moviesform1949>

(:output:
    <moviesform1949><CinemaName>
                burgkino
            </CinemaName></moviesform1949>

  description:
    returns all cinemas which play at least one movie from the year 1949
:)