package exercicios;

import exercicios.base.Aula;
import lombok.NonNull;
import java.util.OptionalDouble;
import java.util.stream.Stream;

/**
 * EstaéumaclassaparavocêpoderimplementarasatividadespropostasnoREADME.
 * Você<b>NÃO</b>devealterar:
 * <ul>
 * <li>aestruturadestearquivo;</li>
 * <li>onomedaclasse,dosmétodosoudosatributos;</li>
 * <li>parâmetrosetipoderetornodosmétodos.</li>
 * </ul>
 *
 * <p>Vocêpodealterarocódigointernodosmétodos,criarmétodosauxiliaresquepodemserchamados
 * pelosexistentes,masnãodevealteraraestruturadosmétodosdisponíveis.</p>
 *
 * @authorManoelCamposdaSilvaFilho
 */
public class Aula04 extends Aula {

    /**
     * Vocêpodechamarosmétodosexistenteseoutrosquevocêcriaraqui,
     * incluirprintsefazeroquedesejarnestemétodoparaconferirosvaloresretornadospeloseumétodo.
     * Paraverificarsesuaimplementaçãoestácorreta,cliquecomobotãodireitononomedoprojetonaabaesquerda
     * doIntelliJeselecioneaopção"RunAllTests".
     */
    public Aula04() {
        final var curso = generator.CURSOS[3];
        final char homem = 'M';
        final char mulher = 'F';

        System.out.printf("MaiornotadetodososEstudantes:%.2f%n", maiorNotaTodosEstudantes(estudantes.stream()));
        System.out.printf("MaiornotadosEstudanteshomens:%.2f%n", maiorNotaHomens(estudantes.stream()));
        System.out.printf("Maiornotadasmulheresdocursode%s:%.2f%n", curso.getNome(), maiorNotaCursoAndSexo(estudantes.stream(), curso, mulher));
        System.out.printf("MédiadenotasdosEstudantesdocursode%s:%.2f%n", curso.getNome(), mediaNotaTodosEstudantesCurso(estudantes.stream(), curso));
        System.out.printf("Totaldoshomensdocursode%s:%d%n", curso.getNome(), totalEstudantesCursoAndSexo(estudantes.stream(), curso, homem));
        System.out.printf("Totaldasmulheresdocursode%s:%d%n", curso.getNome(), totalEstudantesCursoAndSexo(estudantes.stream(), curso, mulher));
    }

    /**
     * Vejaométodoconstrutor{@link#Aula04()}.
     */
    public static void main(String[] args) {
        new Aula04();
    }
    protected double maiorNotaCursoAndSexo(@NonNull final Stream<Estudante> stream, @NonNull final Curso curso, final char sexo) {
        return stream.filter(e -> e.getCurso() != null && e.getCurso().equals(curso) && e.getSexo() == sexo)
                .mapToDouble(Estudante::getNota)
                .max().orElse(-1);
    }

    protected long totalEstudantesCursoAndSexo(@NonNull final Stream<Estudante> stream, @NonNull final Curso curso, final char sexo) {
        return stream.filter(e -> e.getCurso() != null && e.getCurso().equals(curso) && e.getSexo() == sexo).count();
    }

    protected double mediaNotaTodosEstudantesCurso(@NonNull final Stream<Estudante> stream, @NonNull final Curso curso){
        OptionalDouble average = stream.filter(e -> e.getCurso() != null && e.getCurso().equals(curso))
                .mapToDouble(Estudante::getNota)
                .average();
        return average.isPresent() ? average.getAsDouble() : -1;
    }

    protected double maiorNotaTodosEstudantes(@NonNull final Stream<Estudante> stream){
        return stream.mapToDouble(Estudante::getNota).max().orElse(-1);
    }

    protected double maiorNotaHomens(@NonNull final Stream<Estudante> stream){
        return stream.filter(e -> e.getSexo() == 'M')
                .mapToDouble(Estudante::getNota)
                .max().orElse(-1);
    }
}