package com.oguz.cleanmovieapp.presentation.movies

import android.icu.text.StringSearch

/**
 * Data class lar ile ayrı ayrı eventleri tutabilirim
 * event -> bizim için yapılan bir aktivite mesela bir search yapılması gibi kullanıcının input verdiği bir durum
 *
 * sealed class yapma sebebimiz o sınıftan bir instance oluşturmayıp tamamen içeriğindekileri kullanacak olmamız.
 * ekosistem olarak android şu anda bunu en yapısal / en mantıklı yöntem olarak benimsiyor.
 */
sealed class MoviesEvent {

    data class Search(val searchString : String) : MoviesEvent()

}