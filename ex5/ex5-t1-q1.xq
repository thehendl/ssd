for $cinema in doc("ex5-t1.xml")//cinema
let $location := $cinema/@location
order by $location
return <Cinema>
    <Name>{string($cinema/cinemaname)}</Name>
    <Location>{string($location)}</Location>
</Cinema>

(:output:
    <Cinema><Name>
              burgkino
          </Name><Location>innere stadt</Location></Cinema>
    <Cinema><Name>
              filmmcasino
          </Name><Location>margarethen</Location></Cinema>

  description:
    all cinemas sorted alpabetical by location
:)