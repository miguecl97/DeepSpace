/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 *
 * @author miguelcant
 * Enumerado representa todos los resultados posibles de un combate entre una estación espacial
y una nave enemiga
 * ENEMYWINS : La nave enemiga gana el combate
 * NOCOMBAT: No se produce el combate
 * STATIONSCAPES: La estación espacial pierde y consigue escapar
 * STATIONWINS: La estación espacial gana el combate
 */
public enum CombatResult {ENEMYWINS, NOCOMBAT, STATIONESCAPES, STATIONWINS,STATIONWINSANDCONVERTS}
